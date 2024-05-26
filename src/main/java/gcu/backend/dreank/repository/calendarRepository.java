package gcu.backend.dreank.repository;

import gcu.backend.dreank.domain.calendar.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface calendarRepository extends JpaRepository<Calendar, Long> {
}
