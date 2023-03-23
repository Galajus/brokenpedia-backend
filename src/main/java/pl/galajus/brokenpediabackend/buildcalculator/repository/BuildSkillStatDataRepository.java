package pl.galajus.brokenpediabackend.buildcalculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.galajus.brokenpediabackend.buildcalculator.model.BuildSkillStatData;

public interface BuildSkillStatDataRepository extends JpaRepository<BuildSkillStatData, Long> {
}