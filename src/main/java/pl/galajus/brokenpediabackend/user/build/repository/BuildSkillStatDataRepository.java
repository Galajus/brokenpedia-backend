package pl.galajus.brokenpediabackend.user.build.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.galajus.brokenpediabackend.user.build.model.BuildSkillStatData;

public interface BuildSkillStatDataRepository extends JpaRepository<BuildSkillStatData, Long> {
}