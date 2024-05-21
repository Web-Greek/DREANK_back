package gcu.backend.dreank.domain.user;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    null 여부, column 이름 - 동일한 경우 생략, 길이
//    null이 가능하고, column명과 변수명 동일하다면 @Column 불필요
    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 40)
    private String nickname;

    @Column(nullable = false, length = 20)
    private String password;

    private String phone;

    private String email;

    private String birth;
    
    private String introduce;
}