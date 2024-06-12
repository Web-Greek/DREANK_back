package gcu.backend.dreank.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gcu.backend.dreank.domain.common.BaseEntity;
import gcu.backend.dreank.domain.mapping.UserStudy;
import gcu.backend.dreank.domain.study.Study;
import gcu.backend.dreank.domain.user.enums.UserStatus;
import gcu.backend.dreank.dto.request.user.UserCreateRequest;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "`user`") // 테이블명을 백틱으로 감쌉니다.
@Setter
@Getter
@Builder
@NoArgsConstructor //(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public User(UserCreateRequest request) {

        this.nickname = request.getNickname();
        this.password = request.getPassword();
        this.phone = request.getPhone();
        this.email = request.getEmail();

        this.status = UserStatus.ACTIVATE;
        this.introduce = request.getIntroduce();
    }

    @Column(nullable = false, length = 20)
    private String nickname;

    @Column(nullable = false, length = 20)
    private String password;

    @Column(nullable = false, length = 20)
    private String phone;

    @Column(nullable = false, length = 40)
    private String email;


//  activated in mysql
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column(length = 100)
    private String introduce;

    //여기 아래부터, 각 class 생성 후에 import 해야 함
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonIgnore
    private List<UserStudy> userStudyList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Study> studyList = new ArrayList<>();

    //@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    //private List<UserChat> userChatList = new ArrayList<>();

    //@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    //private List<Post> postList = new ArrayList<>();

    //@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    //private List<Message> messageList = new ArrayList<>();
}