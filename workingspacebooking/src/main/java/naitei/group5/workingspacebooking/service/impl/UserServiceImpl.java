package naitei.group5.workingspacebooking.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import naitei.group5.workingspacebooking.dto.request.RegisterRequest;
import naitei.group5.workingspacebooking.dto.response.RegisterResponse;
import naitei.group5.workingspacebooking.entity.User;
import naitei.group5.workingspacebooking.repository.UserRepository;
import naitei.group5.workingspacebooking.service.EmailService;
import naitei.group5.workingspacebooking.service.UserService;
import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final EmailService emailService;

    @Override
    public RegisterResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        // Tạo mật khẩu ngẫu nhiên
        String rawPassword = generateRandomPassword(8);

        // Mã hóa mật khẩu
        String encodedPassword = passwordEncoder.encode(rawPassword);

        // Tạo User entity
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .password(encodedPassword)
                .role(request.getRole())
                .build();

        // Lưu DB
        userRepository.save(user);

        // Gửi email mật khẩu
        emailService.sendSimpleMessage(
                user.getEmail(),
                "Your Account Password",
                "Hello " + user.getName() + ",\n\n" +
                        "Your account has been created successfully.\n" +
                        "Your password is: " + rawPassword + "\n\n" +
                        "Please change it after logging in."
        );

        // Trả response
        return RegisterResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    private String generateRandomPassword(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

}
