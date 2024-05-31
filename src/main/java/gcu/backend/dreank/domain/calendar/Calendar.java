package gcu.backend.dreank.domain.calendar;

import gcu.backend.dreank.domain.study.enums.Day;
import gcu.backend.dreank.domain.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Calendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") // 외래키는 user_id
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Day dayOfWeek; // 요일

    @Column(nullable = false, columnDefinition = "DATETIME")
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime startTime; // 시작 시간

    @Column(nullable = false, columnDefinition = "DATETIME")
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime endTime; // 종료 시간

    @Column(nullable = false)
    private boolean isOccupied; // 시간대 사용 여부
}
