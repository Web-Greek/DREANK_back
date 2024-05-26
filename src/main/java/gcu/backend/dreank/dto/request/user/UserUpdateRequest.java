package gcu.backend.dreank.dto.request.user;

import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserUpdateRequest {
    private String nickname;

//    현재 비밀번호 확인
    private String password;

//    새 비밀번호 확인용
    private String new_password;
    private String new_password_;

    private String email;
}
