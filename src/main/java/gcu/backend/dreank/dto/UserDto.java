package gcu.backend.dreank.dto;

import gcu.backend.dreank.domain.user.enums.UserStatus;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String nickname;
    private String password;
    private String phone;
    private String email;
    private String birth;
    private UserStatus status;
    private String introduce;
}
