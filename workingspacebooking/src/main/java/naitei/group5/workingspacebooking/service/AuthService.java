package naitei.group5.workingspacebooking.service;

import jakarta.servlet.http.HttpServletRequest;
import naitei.group5.workingspacebooking.dto.request.LoginRequest;
import naitei.group5.workingspacebooking.dto.request.RefreshRequest;
import naitei.group5.workingspacebooking.dto.response.JwtResponse;

public interface AuthService {
    JwtResponse login(LoginRequest req, HttpServletRequest http);
    JwtResponse refresh(RefreshRequest req);
    void logout(String accessToken);
}
