package gcu.backend.dreank.repository;

import gcu.backend.dreank.domain.study.Study;
import org.springframework.data.jpa.repository.JpaRepository;

public interface studyRepository extends JpaRepository<Study, Long> {
}