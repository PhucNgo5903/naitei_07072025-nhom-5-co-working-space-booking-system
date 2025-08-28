package naitei.group5.workingspacebooking.service;

import naitei.group5.workingspacebooking.dto.response.UserResponse;
import naitei.group5.workingspacebooking.entity.enums.UserRole;

import java.util.List;

public interface AdminService {
    List<UserResponse> getAllUsers();
    List<UserResponse> getUsersByRole(UserRole role);

    UserResponse getUserByEmail(String email);
    UserResponse approveOwner(Integer userId);

    void toggleUserStatus(Integer userId);
}
