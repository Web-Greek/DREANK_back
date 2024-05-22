package gcu.backend.dreank.domain.study;

import gcu.backend.dreank.domain.common.BaseEntity;
import gcu.backend.dreank.domain.study.enums.Day;
import gcu.backend.dreank.domain.study.enums.StudyStatus;
import gcu.backend.dreank.domain.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //  user를 참조하는 N:1관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") //외래키는 user_id
    private User user; //user 객체

    @Column(nullable = false, length = 20)
    private String title;

    @Column(nullable = false, length = 255)
    private String content;

    @Column(nullable = false)
    @ColumnDefault("0")
    private int view;
}