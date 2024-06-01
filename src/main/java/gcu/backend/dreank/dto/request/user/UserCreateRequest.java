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

    public void setEmail(String email) {
        // 이메일 형식이 유효한지 검사
        if (isValidEmail(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Invalid email format");
        }
    }

    public void setPassword(String password) {
        // 비밀번호가 유효한지 검사 (길이 등)
        if (isValidPassword(password)) {
            this.password = password;
        } else {
            throw new IllegalArgumentException("Invalid password");
        }
    }

    // 유효한 이메일인지를 확인하는 메소드
    private boolean isValidEmail(String email) {
        // 이메일 형식을 간단하게 체크하는 정규 표현식 사용
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    // 유효한 비밀번호인지를 확인하는 메소드
    private boolean isValidPassword(String password) {
        // 비밀번호가 6자 이상인지 확인
        return password.length() >= 6;
    }
}
