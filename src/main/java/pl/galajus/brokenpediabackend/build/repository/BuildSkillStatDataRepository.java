package pl.galajus.brokenpediabackend.build.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.galajus.brokenpediabackend.build.model.BuildSkillStatData;

public interface BuildSkillStatDataRepository extends JpaRepository<BuildSkillStatData, Long> {
}