package naitei.group5.workingspacebooking.controller.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import naitei.group5.workingspacebooking.dto.request.RegisterRequest;
import naitei.group5.workingspacebooking.dto.response.RegisterResponse;
import naitei.group5.workingspacebooking.service.UserService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthApiController {

    private final UserService userService;

    @PostMapping("/register-renter")
    public ResponseEntity<RegisterResponse> registerRenter(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(userService.registerRenter(request));
    }

    @PostMapping("/register-owner")
    public ResponseEntity<RegisterResponse> registerOwner(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(userService.registerOwner(request));
    }
}
