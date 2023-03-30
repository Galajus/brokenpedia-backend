package pl.galajus.brokenpediabackend.skill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pl.galajus.brokenpediabackend.skill.model.SkillPsychoEffect;

import java.util.List;

public interface SkillPsychoEffectRepository extends JpaRepository<SkillPsychoEffect, Long> {

    List<SkillPsychoEffect> findAllBySkillBasicIdIn(List<Long> skillBasicId);
    @Query("delete from SkillPsychoEffect c where c.id in (:ids)")
    @Modifying
    void deleteAllByIdIn(List<Long> ids);
}