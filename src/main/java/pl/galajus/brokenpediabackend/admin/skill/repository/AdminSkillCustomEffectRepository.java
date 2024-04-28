package pl.galajus.brokenpediabackend.admin.skill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pl.galajus.brokenpediabackend.admin.skill.model.AdminSkillCustomEffect;

import java.util.List;

public interface AdminSkillCustomEffectRepository extends JpaRepository<AdminSkillCustomEffect, Long> {

    List<AdminSkillCustomEffect> findAllBySkillBasicIdIn(List<Long> ids);
    @Query("delete from AdminSkillCustomEffect c where c.id in (:ids)")
    @Modifying
    void deleteAllByIdIn(List<Long> ids);
}