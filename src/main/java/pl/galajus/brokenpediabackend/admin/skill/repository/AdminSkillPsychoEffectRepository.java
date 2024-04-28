package pl.galajus.brokenpediabackend.admin.skill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pl.galajus.brokenpediabackend.admin.skill.model.AdminSkillPsychoEffect;

import java.util.List;

public interface AdminSkillPsychoEffectRepository extends JpaRepository<AdminSkillPsychoEffect, Long> {

    List<AdminSkillPsychoEffect> findAllBySkillBasicIdIn(List<Long> skillBasicId);
    @Query("delete from AdminSkillPsychoEffect c where c.id in (:ids)")
    @Modifying
    void deleteAllByIdIn(List<Long> ids);
}