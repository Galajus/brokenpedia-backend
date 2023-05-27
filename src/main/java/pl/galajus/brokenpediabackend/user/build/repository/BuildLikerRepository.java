package pl.galajus.brokenpediabackend.user.build.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.galajus.brokenpediabackend.user.build.model.BuildLiker;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BuildLikerRepository extends JpaRepository<BuildLiker, UUID> {

    Optional<BuildLiker> findByBuildIdAndLikerUuid(Long buildId, UUID likerUuid);
    List<BuildLiker> findByBuildIdIn(List<Long> ids);
    List<BuildLiker> findByBuildId(Long id);

}