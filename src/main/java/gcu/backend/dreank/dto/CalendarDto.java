package gcu.backend.dreank.dto;

import gcu.backend.dreank.domain.study.enums.Day;
import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalendarDto {
    private Long id;
    private Long userId;
    private String title;
    private String description;
    private Day dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean isOccupied;
}
