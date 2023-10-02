package pl.galajus.brokenpediabackend.user.orb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.galajus.brokenpediabackend.user.orb.model.Orb;

public interface OrbRepository extends JpaRepository<Orb, Long> {
}
