package gcu.backend.dreank.domain.calendar;

import gcu.backend.dreank.domain.common.BaseEntity;
import gcu.backend.dreank.domain.study.enums.Day;
import gcu.backend.dreank.domain.study.Study;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
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
public class Calendar extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // study를 참조하는 N:1 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id", nullable = false) // 외래키는 study_id
    private Study study;

    @Column(length = 500)
    private String detail;

    // color는 추가할지말지 미정
    private String color;

    @ColumnDefault("false")
    private boolean deleted = false;

    // js 캘린더 맞춰서 datetime 형식 변경필요할 수도 있음
    @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt = LocalDateTime.now();

    private String createdBy;

}


