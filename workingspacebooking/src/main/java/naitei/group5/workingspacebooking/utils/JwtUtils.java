package naitei.group5.workingspacebooking.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtils {

    @Value("${app.jwt.secret}")           private String secret; // base64
    @Value("${app.jwt.access-exp-ms}")    private long accessExpMs;   // 15 * 60 * 1000
    @Value("${app.jwt.refresh-exp-ms}")   private long refreshExpMs;  // 7 * 24 * 60 * 60 * 1000

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    public String generateAccessToken(String subjectEmail, String role, Integer sessionId) {
        return baseBuilder(subjectEmail, role, sessionId, accessExpMs).compact();
    }

    public String generateRefreshToken(String subjectEmail, String role, Integer sessionId) {
        return baseBuilder(subjectEmail, role, sessionId, refreshExpMs).compact();
    }

    private JwtBuilder baseBuilder(String email, String role, Integer sessionId, long ttl) {
        long now = System.currentTimeMillis();
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + ttl))
                .addClaims(Map.of("role", role, "sid", sessionId))
                .signWith(key(), SignatureAlgorithm.HS256);
    }

    public Jws<Claims> parse(String token) {
        return Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token);
    }

    public String getEmail(String token) { return parse(token).getBody().getSubject(); }
    public String getRole(String token)  { return (String) parse(token).getBody().get("role"); }
    public Integer getSessionId(String token) { return (Integer) parse(token).getBody().get("sid"); }
    public boolean isExpired(String token) { return parse(token).getBody().getExpiration().before(new Date()); }
}
