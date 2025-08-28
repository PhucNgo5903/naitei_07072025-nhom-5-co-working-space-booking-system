package naitei.group5.workingspacebooking.service.impl;

import lombok.RequiredArgsConstructor;
import naitei.group5.workingspacebooking.dto.response.UserResponse;
import naitei.group5.workingspacebooking.entity.User;
import naitei.group5.workingspacebooking.entity.enums.UserRole;
import naitei.group5.workingspacebooking.repository.UserRepository;
import naitei.group5.workingspacebooking.service.AdminService;
import naitei.group5.workingspacebooking.service.EmailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final EmailService emailService;

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserResponse> getUsersByRole(UserRole role) {
        return userRepository.findByRole(role)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private UserResponse mapToResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .role(user.getRole())
                .deleted(user.getDeleted())
                .build();
    }

    @Override
    public UserResponse getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email " + email));
        return mapToResponse(user);
    }

    @Override
    public UserResponse approveOwner(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));

        if (user.getRole() != UserRole.pending_owner) {
            throw new RuntimeException("User is not pending_owner");
        }

        user.setRole(UserRole.owner);
        User saved = userRepository.save(user);

        emailService.sendSimpleMessage(
                user.getEmail(),
                "Phê duyệt trở thành chủ sở hữu",
                "Xin chào " + user.getName() + ",\n\n" +
                        "Yêu cầu trở thành chủ sở hữu của bạn đã được phê duyệt. Giờ bạn đã có quyền owner.\n\n" +
                        "Trân trọng,\nĐội ngũ hỗ trợ."
        );

        return mapToResponse(saved);
    }


    @Override
    @Transactional
    public void toggleUserStatus(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));

        if (user.getRole() == UserRole.admin) {
            throw new RuntimeException("Cannot change status of admin account");
        }

        boolean currentlyDeleted = Boolean.TRUE.equals(user.getDeleted());
        user.setDeleted(!currentlyDeleted);
        user.setDeletedAt(!currentlyDeleted ? LocalDateTime.now() : null);

        userRepository.save(user);

        // ===== Send email notification =====
        String subject;
        String body;

        if (!currentlyDeleted) { // vừa bị disable
            subject = "Tài khoản của bạn đã bị vô hiệu hóa";
            body = "Xin chào " + user.getName() + ",\n\n"
                    + "Tài khoản của bạn đã bị vô hiệu hóa bởi quản trị viên. "
                    + "Bạn sẽ không thể đăng nhập hoặc sử dụng dịch vụ cho đến khi được kích hoạt lại.\n\n"
                    + "Trân trọng,\nĐội ngũ hỗ trợ.";
        } else { // vừa được enable lại
            subject = "Tài khoản của bạn đã được kích hoạt lại";
            body = "Xin chào " + user.getName() + ",\n\n"
                    + "Tài khoản của bạn đã được kích hoạt lại. "
                    + "Bây giờ bạn có thể đăng nhập và tiếp tục sử dụng dịch vụ.\n\n"
                    + "Trân trọng,\nĐội ngũ hỗ trợ.";
        }

        emailService.sendSimpleMessage(user.getEmail(), subject, body);
    }

}
