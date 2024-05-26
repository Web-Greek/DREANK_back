package gcu.backend.dreank.repository;

import gcu.backend.dreank.domain.study.Study;
import gcu.backend.dreank.domain.study.enums.StudyStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyRepository extends JpaRepository<Study, Long> {
    List<Study> findByName(String name);
    List<Study> findTop3ByOrderByPointDesc();
    List<Study> findByStatus(StudyStatus status);
}
