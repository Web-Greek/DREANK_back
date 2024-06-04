package gcu.backend.dreank.repository;

import gcu.backend.dreank.domain.calendar.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import gcu.backend.dreank.domain.study.enums.Day;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Long> {
    List<Calendar> findByUserId(Long userId);

    // 겹치는 일정이 있는지 확인
    @Query("SELECT c FROM Calendar c WHERE c.user.id = :userId AND c.dayOfWeek = :dayOfWeek AND c.startTime < :endTime AND c.endTime > :startTime")
    List<Calendar> checkDuplication(@Param("userId") Long userId, @Param("dayOfWeek") Day dayOfWeek, @Param("endTime") LocalTime endTime, @Param("startTime") LocalTime startTime);
}
