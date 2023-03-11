package pl.galajus.brokenpediabackend.buildcalculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.galajus.brokenpediabackend.buildcalculator.model.SkillCustomEffect;

public interface SkillCustomEffectRepository extends JpaRepository<SkillCustomEffect, Long> {
}