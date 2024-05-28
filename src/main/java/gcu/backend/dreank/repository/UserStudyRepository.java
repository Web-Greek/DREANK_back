package gcu.backend.dreank.repository;

import gcu.backend.dreank.domain.mapping.UserStudy;
import gcu.backend.dreank.domain.study.Study;
import gcu.backend.dreank.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserStudyRepository extends JpaRepository<UserStudy, Long> {
    Optional<UserStudy> findByUserAndStudy(User user, Study study);
}
