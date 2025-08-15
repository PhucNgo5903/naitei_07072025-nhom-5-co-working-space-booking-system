package naitei.group5.workingspacebooking.service;

import naitei.group5.workingspacebooking.dto.request.RegisterRequest;
import naitei.group5.workingspacebooking.dto.response.RegisterResponse;

public interface UserService {
    RegisterResponse register(RegisterRequest request);
}
