package gcu.backend.dreank.domain.user;

import gcu.backend.dreank.domain.common.BaseEntity;
import gcu.backend.dreank.domain.mapping.UserStudy;
import gcu.backend.dreank.domain.study.Study;
import gcu.backend.dreank.domain.user.enums.UserStatus;
import gcu.backend.dreank.web.dto.request.UserCreateRequest;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public User(UserCreateRequest request) {
        this.name = request.getName();
        this.nickname = request.getNickname();
        this.password = request.getPassword();
        this.phone = request.getPhone();
        this.email = request.getEmail();
        this.birth = request.getBirth();
        this.status = UserStatus.ACTIVATE;
        this.introduce = request.getIntroduce();
    }
//    null 여부, column 이름 - 동일한 경우 생략, 길이
//    null이 가능하고, column명과 변수명 동일하다면 @Column 불필요

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 20)
    private String nickname;

    @Column(nullable = false, length = 20)
    private String password;

    @Column(nullable = false, length = 20)
    private String phone;

    @Column(nullable = false, length = 40)
    private String email;

//    수정- 년월까지만,,,
//    @Column(nullable = false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private String birth;

//  activated in mysql
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column(nullable = false, length = 100)
    private String introduce;

    //여기 아래부터, 각 class 생성 후에 import 해야 함
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserStudy> userStudyList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Study> studyList = new ArrayList<>();

    //@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    //private List<UserChat> userChatList = new ArrayList<>();

    //@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    //private List<Post> postList = new ArrayList<>();

    //@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    //private List<Message> messageList = new ArrayList<>();
}