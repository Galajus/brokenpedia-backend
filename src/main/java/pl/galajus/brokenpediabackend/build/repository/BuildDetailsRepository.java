package pl.galajus.brokenpediabackend.build.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.galajus.brokenpediabackend.build.model.BuildDetails;

public interface BuildDetailsRepository extends JpaRepository<BuildDetails, Long> {
}