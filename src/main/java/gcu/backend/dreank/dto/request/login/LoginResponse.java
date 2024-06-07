package gcu.backend.dreank.dto.request.login;

public class LoginResponse {
    private Long id;
    private String email;
    private String nickname;

    // 생성자
    public LoginResponse(Long id,String email, String nickname) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
    }

    public Long getUser_id() {
        return id;
    }

    public Long setUser_id() {
        return id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
