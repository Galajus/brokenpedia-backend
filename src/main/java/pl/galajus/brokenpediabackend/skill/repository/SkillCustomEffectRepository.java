package pl.galajus.brokenpediabackend.skill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pl.galajus.brokenpediabackend.skill.model.SkillCustomEffect;

import java.util.List;

public interface SkillCustomEffectRepository extends JpaRepository<SkillCustomEffect, Long> {

    List<SkillCustomEffect> findAllBySkillBasicIdIn(List<Long> ids);
    @Query("delete from SkillCustomEffect c where c.id in (:ids)")
    @Modifying
    void deleteAllByIdIn(List<Long> ids);
}