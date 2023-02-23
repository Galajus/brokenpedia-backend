package pl.galajus.brokenpediabackend.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.galajus.brokenpediabackend.security.model.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

}
