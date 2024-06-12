package gcu.backend.dreank.service.Calendar;

import gcu.backend.dreank.domain.study.Tag;
import gcu.backend.dreank.repository.TagRepository;
import gcu.backend.dreank.domain.calendar.Calendar;
import gcu.backend.dreank.domain.calendar.TimeSlot;
import gcu.backend.dreank.domain.study.Study;
import gcu.backend.dreank.domain.user.User;
import gcu.backend.dreank.domain.study.enums.Day;
import gcu.backend.dreank.dto.CalendarRequest;
import gcu.backend.dreank.repository.CalendarRepository;
import gcu.backend.dreank.repository.UserRepository;
import gcu.backend.dreank.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CalendarService {

    private final CalendarRepository calendarRepository;
    private final UserRepository userRepository;
    private final StudyRepository studyRepository;
    private final TagRepository tagRepository;


    @Transactional
    public Calendar createCalendarEntry(CalendarRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // 중복 일정 확인
        List<Calendar> overlappingCalendars = calendarRepository.checkDuplication(
                request.getUserId(),
                request.getDayOfWeek(),
                request.getEndTime(),
                request.getStartTime()
        );

        if (!overlappingCalendars.isEmpty()) {
            throw new IllegalArgumentException("해당 시간에 등록된 일정이 이미 존재합니다. 시간을 수정해 주세요");
        }

        Calendar calendar = Calendar.builder()
                .user(user)
                .activityName(request.getActivityName())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .color(request.getColor())
                .dayOfWeek(request.getDayOfWeek())
                .build();

        return calendarRepository.save(calendar);
    }

    @Transactional
    public Calendar updateCalendarEntry(Long id, CalendarRequest request) {
        Calendar calendar = calendarRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Calendar entry not found"));

        // 중복 일정 확인 (업데이트 시에도 확인)
        List<Calendar> overlappingCalendars = calendarRepository.checkDuplication(
                calendar.getUser().getId(),
                request.getDayOfWeek(),
                request.getEndTime(),
                request.getStartTime()
        );

        if (!overlappingCalendars.isEmpty()) {
            throw new IllegalArgumentException("해당 시간에 등록된 일정이 이미 존재합니다. 시간을 수정해 주세요");
        }

        calendar.updateCalendar(
                request.getActivityName(),
                request.getStartTime(),
                request.getEndTime(),
                request.getColor(),
                request.getDayOfWeek()
        );


        return calendarRepository.save(calendar);
    }

    @Transactional
    public void deleteCalendarEntry(Long id) {
        calendarRepository.deleteById(id);
    }

    public List<Calendar> getUserCalendar(Long userId) {
        return calendarRepository.findByUserId(userId);
    }

    @Transactional(readOnly = true)
    public List<Study> findOverlappingStudyGroups(Long userId) {
        List<Calendar> userCalendar = calendarRepository.findByUserId(userId);
        List<Study> allStudies = studyRepository.findAll();

        List<Study> overlappingStudies = new ArrayList<>();

        // 사용자 자유 시간대 계산
        List<TimeSlot> freeTimeSlots = calculateFreeTime(userCalendar);

        // 스터디 그룹 일정과 비교
        for (Study study : allStudies) {
            for (TimeSlot freeSlot : freeTimeSlots) {
                if (isOverlapping(freeSlot, study)) {
                    overlappingStudies.add(study);
                    break;
                }
            }
        }

        return overlappingStudies;
    }

    // 태그, 요일, 시간으로 스터디 그룹 찾기
    @Transactional(readOnly = true)
    public List<Study> findMatchingStudyGroups(Long userId, String tagContent, Day preferredDay, LocalTime preferredStartTime, LocalTime preferredEndTime) {
        List<Calendar> userCalendar = calendarRepository.findByUserId(userId);
        List<Study> studies;

        if (tagContent != null && !tagContent.isEmpty()) {
            studies = studyRepository.findAll().stream()
                    .filter(study -> study.getTag().equalsIgnoreCase(tagContent))
                    .collect(Collectors.toList());
        } else {
            studies = studyRepository.findAll();
        }

        List<TimeSlot> freeTimeSlots = calculateFreeTime(userCalendar);
        List<Study> matchingStudies = new ArrayList<>();

        for (Study study : studies) {
            if (Day.valueOf(study.getDay()).equals(preferredDay) && isTimeSlotMatching(study, preferredStartTime, preferredEndTime)) {
                for (TimeSlot freeSlot : freeTimeSlots) {
                    if (isOverlapping(freeSlot, study)) {
                        matchingStudies.add(study);
                        break;
                    }
                }
            }
        }

        return matchingStudies;
    }

    private boolean isTimeSlotMatching(Study study, LocalTime preferredStartTime, LocalTime preferredEndTime) {
        return !study.getStartTime().isAfter(preferredEndTime) && !study.getEndTime().isBefore(preferredStartTime);
    }


    private List<TimeSlot> calculateFreeTime(List<Calendar> userCalendar) {
        List<TimeSlot> freeTimeSlots = new ArrayList<>();

        LocalTime dayStart = LocalTime.of(0, 0);
        LocalTime dayEnd = LocalTime.of(23, 59);

        // 요일별로 사용자 일정 정렬
        userCalendar.sort((c1, c2) -> {
            if (c1.getDayOfWeek().ordinal() == c2.getDayOfWeek().ordinal()) {
                return c1.getStartTime().compareTo(c2.getStartTime());
            }
            return Integer.compare(c1.getDayOfWeek().ordinal(), c2.getDayOfWeek().ordinal());
        });

        // 사용자 자유 시간대 계산
        for (Day day : Day.values()) {
            LocalTime lastEndTime = dayStart;

            for (Calendar calendar : userCalendar) {
                if (calendar.getDayOfWeek() == day) {
                    // 자유 시간대 계산
                    if (calendar.getStartTime().isAfter(lastEndTime)) {
                        // 자유 시간대 추가
                        freeTimeSlots.add(new TimeSlot(lastEndTime, calendar.getStartTime(), day));
                    }
                    lastEndTime = calendar.getEndTime();
                }
            }

            if (lastEndTime.isBefore(dayEnd)) {
                // 자유 시간대 추가 로직
                freeTimeSlots.add(new TimeSlot(lastEndTime, dayEnd, day));
            }
        }

        return freeTimeSlots;
    }

    private boolean isOverlapping(TimeSlot freeSlot, Study study) {
        return freeSlot.getStartTime().isBefore(study.getEndTime()) && freeSlot.getEndTime().isAfter(study.getStartTime());
    }
}
