package naitei.group5.workingspacebooking.repository;

import naitei.group5.workingspacebooking.entity.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import naitei.group5.workingspacebooking.entity.User;
import org.springframework.lang.NonNull;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    List<User> findByRole(UserRole role);

    @NonNull
    Optional<User> findById(@NonNull Integer id);
    
    List<User> findAllByRoleIn(Collection<UserRole> roles);

    @NonNull
    Optional<User> findByIdAndDeletedFalse(@NonNull Integer id);

}
