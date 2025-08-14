package sun.naitei.group5.workingspacebooking.service;

import sun.naitei.group5.workingspacebooking.dto.request.RegisterRequest;
import sun.naitei.group5.workingspacebooking.dto.response.RegisterResponse;

public interface UserService {
    RegisterResponse register(RegisterRequest request);
}
