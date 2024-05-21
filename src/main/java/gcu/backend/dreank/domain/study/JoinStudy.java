package gcu.backend.dreank.domain.study;

import gcu.backend.dreank.domain.common.BaseEntity;
import gcu.backend.dreank.domain.study.enums.StudyStatus;
import gcu.backend.dreank.domain.study.enums.Verify;
import gcu.backend.dreank.domain.user.User;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

public class JoinStudy extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //  user를 참조하는 N:1관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") //외래키는 user_id
    private User user; //user 객체

    //  user를 참조하는 N:1관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id") //외래키는 leader_id
    private Study study;

    @Column(nullable = false)
    @ColumnDefault("REJECT")
    @Enumerated(EnumType.STRING)
    private Verify verify;
}
