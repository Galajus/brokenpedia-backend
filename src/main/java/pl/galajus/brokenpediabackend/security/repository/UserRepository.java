package pl.galajus.brokenpediabackend.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.galajus.brokenpediabackend.security.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByUsername(String username);
    Optional<User> findByUuid(UUID uuid);
    Optional<User> findByConfirmAccountHash(String hash);
    Optional<User> findByLostPasswordHash(String hash);
    boolean existsByUsername(String username);
    boolean existsByNickname(String nickname);


}
