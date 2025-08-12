package naitei.group5.workingspacebooking.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JwtResponse{
    private String accessToken;
    private String refreshToken;
    private Integer userId;
    private String name;
    private String role;
}
