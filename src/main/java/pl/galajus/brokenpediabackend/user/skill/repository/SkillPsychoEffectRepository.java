package pl.galajus.brokenpediabackend.user.skill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.galajus.brokenpediabackend.user.skill.model.SkillPsychoEffect;

import java.util.List;

public interface SkillPsychoEffectRepository extends JpaRepository<SkillPsychoEffect, Long> {

    List<SkillPsychoEffect> findAllBySkillBasicIdIn(List<Long> skillBasicId);
}