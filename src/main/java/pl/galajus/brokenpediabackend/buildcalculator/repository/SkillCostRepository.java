package pl.galajus.brokenpediabackend.buildcalculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.galajus.brokenpediabackend.buildcalculator.model.SkillCost;

public interface SkillCostRepository extends JpaRepository<SkillCost, Long> {
}