package gcu.backend.dreank.domain.study;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class Study {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    user 참조
    private Long leader_id;

    private LocalDateTime created_at;

//    시작 시간, 끝시간 *시간*으로 받기?
//    private start_time;
//    private end_time;

//     요일,,,enum?
//    private  day;

//   평가 점수
    private int score;

    //     모집중/모집완료/
    @Enumerated(EnumType.STRING)
    private StudyStatus status;
    
}
