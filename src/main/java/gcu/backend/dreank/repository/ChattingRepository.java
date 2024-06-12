package gcu.backend.dreank.repository;

import gcu.backend.dreank.domain.calendar.Calendar;
import gcu.backend.dreank.domain.chat.Chatting;
import gcu.backend.dreank.domain.study.Study;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChattingRepository extends JpaRepository<Chatting, Long> {

}
