package repository;

import model.ClassroomSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessionRepository extends JpaRepository<ClassroomSession, Long> {
    Optional<ClassroomSession> findByJoinCode(String joinCode);
}
