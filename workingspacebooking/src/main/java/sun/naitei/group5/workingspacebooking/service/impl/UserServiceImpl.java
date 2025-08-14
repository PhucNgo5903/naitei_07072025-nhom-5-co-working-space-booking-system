package sun.naitei.group5.workingspacebooking.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import sun.naitei.group5.workingspacebooking.dto.request.RegisterRequest;
import sun.naitei.group5.workingspacebooking.dto.response.RegisterResponse;
import sun.naitei.group5.workingspacebooking.entity.User;
import sun.naitei.group5.workingspacebooking.entity.enums.UserRole;
import sun.naitei.group5.workingspacebooking.repository.UserRepository;
import sun.naitei.group5.workingspacebooking.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public RegisterResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .role(request.getRole())
                .build();

        User savedUser = userRepository.save(user);

        return RegisterResponse.builder()
                .id(savedUser.getId())
                .name(savedUser.getName())
                .email(savedUser.getEmail())
                .phone(savedUser.getPhone())
                .role(savedUser.getRole())
                .build();
    }
}
