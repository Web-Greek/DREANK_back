package gcu.backend.dreank.web.dto.request;

import lombok.Getter;

@Getter
public class UserUpdateRequest {
    private Long id;
    private String nickname;
    private String password;
    private String email;

}
