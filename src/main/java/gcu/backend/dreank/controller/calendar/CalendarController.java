package gcu.backend.dreank.controller.calendar;

import gcu.backend.dreank.domain.calendar.Calendar;
import gcu.backend.dreank.domain.study.Study;
import gcu.backend.dreank.dto.CalendarRequest;
import gcu.backend.dreank.service.Calendar.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/{userId}/calendar")
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;

    @PostMapping
    public ResponseEntity<Calendar> createCalendarEntry(@PathVariable Long userId, @RequestBody CalendarRequest request) {
        request.setUserId(userId);
        Calendar calendar = calendarService.createCalendarEntry(request);
        return ResponseEntity.ok(calendar);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Calendar> updateCalendarEntry(@PathVariable Long userId, @PathVariable Long id, @RequestBody CalendarRequest request) {
        request.setUserId(userId);
        Calendar calendar = calendarService.updateCalendarEntry(id, request);
        return ResponseEntity.ok(calendar);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCalendarEntry(@PathVariable Long userId, @PathVariable Long id) {
        calendarService.deleteCalendarEntry(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Calendar>> getUserCalendar(@PathVariable Long userId) {
        List<Calendar> calendar = calendarService.getUserCalendar(userId);
        return ResponseEntity.ok(calendar);
    }

    @GetMapping("/overlapping-studies")
    public ResponseEntity<List<Study>> findOverlappingStudyGroups(@PathVariable Long userId) {
        List<Study> overlappingStudies = calendarService.findOverlappingStudyGroups(userId);
        return ResponseEntity.ok(overlappingStudies);
    }
}
