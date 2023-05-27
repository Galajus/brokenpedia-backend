package pl.galajus.brokenpediabackend.user.skill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.galajus.brokenpediabackend.user.skill.model.SkillCost;

public interface SkillCostRepository extends JpaRepository<SkillCost, Long> {
}