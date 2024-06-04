package gcu.backend.dreank.controller.calendar;

import gcu.backend.dreank.domain.calendar.Calendar;
import gcu.backend.dreank.domain.study.Study;
import gcu.backend.dreank.domain.study.enums.Day;
import gcu.backend.dreank.dto.CalendarRequest;
import gcu.backend.dreank.service.Calendar.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
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

    @PatchMapping("/{id}")
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

    @GetMapping("/searchGroupUsingCalendar")
    public ResponseEntity<List<Study>> findOverlappingStudyGroups(@PathVariable Long userId) {
        List<Study> overlappingStudies = calendarService.findOverlappingStudyGroups(userId);
        if (overlappingStudies.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(overlappingStudies);
    }

    @GetMapping("/filterByTagAndTime")
    public ResponseEntity<?> filterByTagAndTime(@PathVariable Long userId, @RequestParam String tagContent, @RequestParam Day preferredDay, @RequestParam String preferredStartTime, @RequestParam String preferredEndTime) {
        List<Study> matchingStudies = calendarService.findMatchingStudyGroups(userId, tagContent, preferredDay, LocalTime.parse(preferredStartTime), LocalTime.parse(preferredEndTime));
        if (matchingStudies.isEmpty()) {
            return ResponseEntity.ok("조건에 맞는 스터디가 없습니다");
        }
        return ResponseEntity.ok(matchingStudies);
    }
}
