package naitei.group5.workingspacebooking.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import naitei.group5.workingspacebooking.dto.request.LoginRequest;
import naitei.group5.workingspacebooking.dto.request.RefreshRequest;
import naitei.group5.workingspacebooking.dto.response.JwtResponse;
import naitei.group5.workingspacebooking.entity.User;
import naitei.group5.workingspacebooking.entity.UserSession;
import naitei.group5.workingspacebooking.repository.UserRepository;
import naitei.group5.workingspacebooking.repository.UserSessionRepository;
import naitei.group5.workingspacebooking.service.AuthService;
import naitei.group5.workingspacebooking.service.TokenBlacklist;
import naitei.group5.workingspacebooking.utils.JwtUtils;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepo;
    private final UserSessionRepository sessionRepo;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();;
    private final JwtUtils jwt;
    private final TokenBlacklist blacklist;

    @Override
    public JwtResponse login(LoginRequest req, HttpServletRequest http) {
        User user = userRepo.findByEmail(req.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));
        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        // tạo phiên
        UserSession session = new UserSession();
        session.setUser(user);
        session.setLoginTime(LocalDateTime.now());
        session.setIp(getClientIp(http));
        session = sessionRepo.save(session);

        String access = jwt.generateAccessToken(user.getEmail(), user.getRole().name(), session.getId());
        String refresh = jwt.generateRefreshToken(user.getEmail(), user.getRole().name(), session.getId());

        return JwtResponse.builder()
                .accessToken(access)
                .refreshToken(refresh)
                .userId(user.getId())
                .name(user.getName())
                .role(user.getRole().name())
                .build();
    }

    @Override
    public JwtResponse refresh(RefreshRequest req) {
        var claims = jwt.parse(req.getRefreshToken()).getBody();
        if (jwt.isExpired(req.getRefreshToken())) {
            throw new RuntimeException("Refresh token expired");
        }
        Integer sid = (Integer) claims.get("sid");
        if (blacklist.isRevoked(sid)) throw new RuntimeException("Session revoked");
        // phiên còn mở?
        sessionRepo.findByIdAndLogoutTimeIsNull(sid)
                .orElseThrow(() -> new RuntimeException("Session not active"));

        String email = claims.getSubject();
        String role  = (String) claims.get("role");
        User user = userRepo.findByEmail(email).orElseThrow();

        String newAccess  = jwt.generateAccessToken(email, role, sid);
        String newRefresh = jwt.generateRefreshToken(email, role, sid);

        return JwtResponse.builder()
                .accessToken(newAccess)
                .refreshToken(newRefresh)
                .userId(user.getId())
                .name(user.getName())
                .role(role)
                .build();
    }

    @Override
    public void logout(String accessToken) {
        var c = jwt.parse(accessToken).getBody();
        Integer sid = (Integer) c.get("sid");
        // đóng phiên trong DB
        sessionRepo.findById(sid).ifPresent(s -> {
            if (s.getLogoutTime() == null) s.setLogoutTime(LocalDateTime.now());
            sessionRepo.save(s);
        });
        // revoke (TTL = thời gian còn lại của access token)
        long ttl = c.getExpiration().getTime() - System.currentTimeMillis();
        if (ttl < 0) ttl = 0;
        blacklist.revokeSession(sid, ttl);
    }

    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip != null && !ip.isBlank()) return ip.split(",")[0].trim();
        return request.getRemoteAddr();
    }
}
