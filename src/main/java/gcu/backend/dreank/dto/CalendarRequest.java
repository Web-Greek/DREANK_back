package gcu.backend.dreank.dto;

import lombok.*;
import gcu.backend.dreank.domain.study.enums.Day;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CalendarRequest {
    private Long userId;
    private String activityName;

    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime startTime;

    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime endTime;

    private String color;
    private Day dayOfWeek;
}