package gcu.backend.dreank.domain.mapping;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gcu.backend.dreank.domain.common.BaseEntity;
import gcu.backend.dreank.domain.study.Study;
import gcu.backend.dreank.domain.study.enums.StudyStatus;
import gcu.backend.dreank.domain.study.enums.Verify;
import gcu.backend.dreank.domain.user.User;
import gcu.backend.dreank.domain.user.enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserStudy extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public UserStudy(User user, Study study) {
        this.user = user;
        this.study = study;
        this.verify = Verify.WAITING;
    }

    //  user를 참조하는 N:1관계
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") //외래키는 user_id
    private User user; //user 객체

    //  user를 참조하는 N:1관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id") //외래키는 study_id
    private Study study;

    @Column(nullable = false)
    @ColumnDefault("'REJECT'")
    @Enumerated(EnumType.STRING)
    private Verify verify;


}
