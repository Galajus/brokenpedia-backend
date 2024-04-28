package pl.galajus.brokenpediabackend.user.skill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.galajus.brokenpediabackend.user.skill.model.SkillCustomEffect;

import java.util.List;

public interface SkillCustomEffectRepository extends JpaRepository<SkillCustomEffect, Long> {

    List<SkillCustomEffect> findAllBySkillBasicIdIn(List<Long> ids);
}