package naitei.group5.workingspacebooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import naitei.group5.workingspacebooking.entity.UserSession;
import java.util.Optional;

public interface UserSessionRepository extends JpaRepository<UserSession, Integer> {
    Optional<UserSession> findByIdAndLogoutTimeIsNull(Integer id);
}
