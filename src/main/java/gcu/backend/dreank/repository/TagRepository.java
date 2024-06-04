package gcu.backend.dreank.repository;

import gcu.backend.dreank.domain.study.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findByContent(String content);
}
