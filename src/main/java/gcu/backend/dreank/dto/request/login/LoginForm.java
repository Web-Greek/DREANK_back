package gcu.backend.dreank.dto.request.login;

import lombok.Data;

@Data
public class LoginForm {
    private String email;
    private String password;
}
