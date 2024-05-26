package gcu.backend.dreank.service.CalendarService;

import gcu.backend.dreank.domain.calendar.Calendar;
import gcu.backend.dreank.domain.study.Study;
import gcu.backend.dreank.web.dto.CalendarDto;
import gcu.backend.dreank.repository.calendarRepository;
import gcu.backend.dreank.repository.studyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CalendarService {

    private final calendarRepository calendarRepository;
    private final studyRepository studyRepository;

    @Transactional
    public CalendarDto createEvent(CalendarDto eventDto) {
        Study study = studyRepository.findById(eventDto.getStudyId())
                .orElseThrow(() -> new IllegalArgumentException("Study not found"));

        Calendar event = Calendar.builder()
                .study(study)
                .title(eventDto.getTitle())
                .description(eventDto.getDescription())
                .startTime(eventDto.getStartTime())
                .endTime(eventDto.getEndTime())
                .build();

        calendarRepository.save(event);

        eventDto.setId(event.getId());
        return eventDto;
    }

    @Transactional
    public CalendarDto updateEvent(Long eventId, CalendarDto eventDto) {
        Calendar event = calendarRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));

        event = Calendar.builder()
                .study(studyRepository.findById(eventDto.getStudyId())
                        .orElseThrow(() -> new IllegalArgumentException("Study not found")))
                .title(eventDto.getTitle())
                .description(eventDto.getDescription())
                .startTime(eventDto.getStartTime())
                .endTime(eventDto.getEndTime())
                .build();

        calendarRepository.save(event);

        return eventDto;
    }


    @Transactional
    public void deleteEvent(Long eventId) {
        Calendar event = calendarRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));
        calendarRepository.delete(event);
    }
}
