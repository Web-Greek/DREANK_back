package gcu.backend.dreank.repository;

import gcu.backend.dreank.domain.mapping.UserStudy;
import gcu.backend.dreank.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailAndPassword(String email, String password);
    Optional<User> findByNickname(String nickname);
    boolean existsByNickname(String nickname);

}
