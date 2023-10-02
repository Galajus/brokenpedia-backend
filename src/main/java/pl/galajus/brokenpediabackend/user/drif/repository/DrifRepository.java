package pl.galajus.brokenpediabackend.user.drif.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.galajus.brokenpediabackend.user.drif.model.Drif;

public interface DrifRepository extends JpaRepository<Drif, Long> {
}
