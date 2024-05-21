package gcu.backend.dreank.domain.study;

import gcu.backend.dreank.domain.study.enums.Day;
import gcu.backend.dreank.domain.study.enums.StudyStatus;
import gcu.backend.dreank.domain.study.enums.Tag;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class Study {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    수정 user 참조
    private Long leader_id;

    private LocalDateTime created_at;

//    시작 시간, 끝시간 날짜로 일단 받음
    private LocalDateTime start_time;
    private LocalDateTime end_time;
//
//    요일,,,enum-list?????????????????????????????
//    주 2일인 경우
    @Enumerated(EnumType.STRING)
    private Day day;

//   평가 점수
    private int score;

    //     모집중/모집완료/
    @Enumerated(EnumType.STRING)
    private StudyStatus status;

//    category가 tag인게 아닌지,,,?
    @Enumerated(EnumType.STRING)
    private Tag category;

}
