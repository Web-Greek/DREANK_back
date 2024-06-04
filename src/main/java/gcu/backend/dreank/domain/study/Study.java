package gcu.backend.dreank.domain.study;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import gcu.backend.dreank.domain.common.BaseEntity;
import gcu.backend.dreank.domain.mapping.ChatRoom;
import gcu.backend.dreank.domain.mapping.UserStudy;
import gcu.backend.dreank.domain.study.enums.Day;
import gcu.backend.dreank.domain.study.enums.StudyStatus;
import gcu.backend.dreank.domain.user.User;
import gcu.backend.dreank.dto.request.StudyCreateRequest;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Study extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 100)
    private String introduction;

    @Column(nullable = false)
    private int num_recruit;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime start_time;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime end_time;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private Day day;

    @Column(nullable = false)
    @ColumnDefault("0")
    private int score;

    @Column(nullable = false, length = 20)
    @ColumnDefault("'RECRUIT'")
    @Enumerated(EnumType.STRING)
    private StudyStatus status;


    //양방향 매핑
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leader_id") //외래키는 leader_id
    @JsonBackReference
    private User user; //user 객체

    @OneToMany(mappedBy = "study", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Tag> tagList = new ArrayList<>();

    @OneToMany(mappedBy = "study", cascade = CascadeType.ALL)
    private List<UserStudy> partList = new ArrayList<>();

    @OneToMany(mappedBy = "study", cascade = CascadeType.ALL)
    private List<ChatRoom> chatRoomList = new ArrayList<>();


    // 캘린더에서 추가한 getter 메서드 (2024/05/28 - 이민우)
    public LocalTime getStartTime() {
        return start_time;
    }

    public LocalTime getEndTime() {
        return end_time;
    }
}
