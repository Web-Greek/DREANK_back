package gcu.backend.dreank.domain.calendar;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;

@Getter
@AllArgsConstructor
public class TimeSlot {
    private final LocalTime startTime;
    private final LocalTime endTime;
}
