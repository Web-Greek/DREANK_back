package gcu.backend.dreank.service.CalendarService;

import gcu.backend.dreank.domain.calendar.Calendar;
import gcu.backend.dreank.domain.user.User;
import gcu.backend.dreank.dto.CalendarDto;
import gcu.backend.dreank.repository.calendarRepository;
import gcu.backend.dreank.repository.userRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CalendarService {

    private final calendarRepository calendarRepository;
    private final userRepository userRepository;

    @Transactional
    public CalendarDto createEvent(CalendarDto eventDto) {
        User user = userRepository.findById(eventDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Calendar event = Calendar.builder()
                .user(user)
                .title(eventDto.getTitle())
                .description(eventDto.getDescription())
                .dayOfWeek(eventDto.getDayOfWeek())
                .startTime(eventDto.getStartTime())
                .endTime(eventDto.getEndTime())
                .isOccupied(eventDto.isOccupied())
                .build();

        calendarRepository.save(event);

        eventDto.setId(event.getId());
        return eventDto;
    }

    @Transactional
    public CalendarDto updateEvent(Long eventId, CalendarDto eventDto) {
        Calendar event = calendarRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));

        event.setUser(userRepository.findById(eventDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found")));
        event.setTitle(eventDto.getTitle());
        event.setDescription(eventDto.getDescription());
        event.setDayOfWeek(eventDto.getDayOfWeek());
        event.setStartTime(eventDto.getStartTime());
        event.setEndTime(eventDto.getEndTime());
        event.setOccupied(eventDto.isOccupied());

        calendarRepository.save(event);

        return eventDto;
    }

    @Transactional
    public void deleteEvent(Long eventId) {
        Calendar event = calendarRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));
        calendarRepository.delete(event);
    }

    @Transactional(readOnly = true)
    public List<Calendar> getUserWeeklySchedule(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return calendarRepository.findByUser(user);
    }
}
