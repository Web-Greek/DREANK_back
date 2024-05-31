package gcu.backend.dreank.repository;

import gcu.backend.dreank.domain.calendar.Calendar;
import gcu.backend.dreank.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface calendarRepository extends JpaRepository<Calendar, Long> {
    List<Calendar> findByUser(User user);
}
