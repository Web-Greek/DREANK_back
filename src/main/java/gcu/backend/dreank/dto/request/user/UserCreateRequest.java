package gcu.backend.dreank.dto.request.user;

import lombok.Data;

@Data
public class UserCreateRequest {
//    아이디
    private String email;
    private String password;
    private String password_;

    private String nickname;
    private String name;
    private String introduce;
    private String phone;
    private String birth;
}
