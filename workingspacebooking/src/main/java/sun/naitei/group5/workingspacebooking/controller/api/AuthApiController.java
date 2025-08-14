package sun.naitei.group5.workingspacebooking.controller.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.naitei.group5.workingspacebooking.dto.request.RegisterRequest;
import sun.naitei.group5.workingspacebooking.dto.response.RegisterResponse;
import sun.naitei.group5.workingspacebooking.service.UserService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthApiController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(userService.register(request));
    }
}
