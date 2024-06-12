package gcu.backend.dreank.domain.calendar;

import gcu.backend.dreank.domain.user.User;
import gcu.backend.dreank.domain.study.enums.Day;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

@Entity
@Table(name = "`calendar`")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Calendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, length = 100)
    private String activityName;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime startTime;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime endTime;

    @Column(nullable = false, length = 20)
    private String color;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Day dayOfWeek;
    // MON, TUE, WED, THU, FRI, SAT, SUN

    public void updateCalendar(String activityName, LocalTime startTime, LocalTime endTime, String color, Day dayOfWeek) {
        this.activityName = activityName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.color = color;
        this.dayOfWeek = dayOfWeek;
    }
}
