package sun.naitei.group5.workingspacebooking.dto.response;

import lombok.*;
import sun.naitei.group5.workingspacebooking.entity.enums.UserRole;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterResponse {
    private Integer id;
    private String name;
    private String email;
    private String phone;
    private UserRole role;
}
