package pl.galajus.brokenpediabackend.admin.skill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.galajus.brokenpediabackend.admin.skill.model.AdminSkillCost;

public interface AdminSkillCostRepository extends JpaRepository<AdminSkillCost, Long> {
}