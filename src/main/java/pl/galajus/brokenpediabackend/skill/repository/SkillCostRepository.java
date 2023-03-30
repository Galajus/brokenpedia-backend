package pl.galajus.brokenpediabackend.skill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.galajus.brokenpediabackend.skill.model.SkillCost;

public interface SkillCostRepository extends JpaRepository<SkillCost, Long> {
}