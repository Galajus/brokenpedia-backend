package pl.galajus.brokenpediabackend.user.build.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.galajus.brokenpediabackend.user.build.model.BuildDetails;

public interface BuildDetailsRepository extends JpaRepository<BuildDetails, Long> {
}