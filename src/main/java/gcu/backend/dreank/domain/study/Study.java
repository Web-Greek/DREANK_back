package gcu.backend.dreank.domain.study;

import gcu.backend.dreank.domain.common.BaseEntity;
import gcu.backend.dreank.domain.study.enums.Day;
import gcu.backend.dreank.domain.study.enums.StudyStatus;
import gcu.backend.dreank.domain.study.enums.Tag;
import gcu.backend.dreank.domain.user.User;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DialectOverride;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

@Entity
@NoArgsConstructor
public class Study extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//  user를 참조하는 N:1관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leader_id") //외래키는 leader_id
    private User user; //user 객체

//  시작 시간, 끝시간 날짜로 일단 받음
    @Column(nullable = false)
    @DateTimeFormat(pattern =  "HH:mm:ss")
    private LocalTime start_time;

    @Column(nullable = false)
    @DateTimeFormat(pattern =  "HH:mm:ss")
    private LocalTime end_time;

//    요일,,,enum-list?????????????????????????????
//    주 2일인 경우
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Day day;

//   평가 점수
    @Column(nullable = false)
    @ColumnDefault("0")
    private int score;

    //     모집중/모집완료/
    @Column(nullable = false)
    @ColumnDefault("RECRUIT")
    @Enumerated(EnumType.STRING)
    private StudyStatus status;

    //여기 아래부터, 각 class 생성 후에 import 해야 함
    //@OneToMany(mappedBy = "study", cascade = CascadeType.ALL)
    //private List<Tag> tagList = new ArrayList<>();

    //@OneToMany(mappedBy = "study", cascade = CascadeType.ALL)
    //private List<Part> partList = new ArrayList<>();

    //@OneToMany(mappedBy = "study", cascade = CascadeType.ALL)
    //private List<ChatRoom> chatRoomList = new ArrayList<>();
}
