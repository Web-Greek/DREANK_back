package gcu.backend.dreank.repository;

import gcu.backend.dreank.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepository extends JpaRepository<User, Long> {
}
