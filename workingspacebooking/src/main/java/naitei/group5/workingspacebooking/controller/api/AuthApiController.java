package naitei.group5.workingspacebooking.controller.api;

import jakarta.validation.Valid;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import naitei.group5.workingspacebooking.dto.request.RegisterRequest;
import naitei.group5.workingspacebooking.dto.response.RegisterResponse;
import naitei.group5.workingspacebooking.service.UserService;


import naitei.group5.workingspacebooking.dto.request.LoginRequest;
import naitei.group5.workingspacebooking.dto.request.RefreshRequest;
import naitei.group5.workingspacebooking.dto.response.JwtResponse;
import naitei.group5.workingspacebooking.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthApiController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(userService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequest req, HttpServletRequest http) {
        return ResponseEntity.ok(authService.login(req, http));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtResponse> refresh(@Valid @RequestBody RefreshRequest req) {
        return ResponseEntity.ok(authService.refresh(req));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader("Authorization") String bearer) {
        String token = bearer != null && bearer.startsWith("Bearer ") ? bearer.substring(7) : null;
        if (token != null) authService.logout(token);
        return ResponseEntity.noContent().build();
    }
}
