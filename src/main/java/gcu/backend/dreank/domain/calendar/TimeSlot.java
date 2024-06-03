package gcu.backend.dreank.domain.calendar;

import gcu.backend.dreank.domain.study.enums.Day;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
public class TimeSlot {
    private LocalTime startTime;
    private LocalTime endTime;
    private Day day;
}
