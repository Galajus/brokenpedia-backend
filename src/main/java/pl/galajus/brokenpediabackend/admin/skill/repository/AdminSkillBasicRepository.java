package pl.galajus.brokenpediabackend.admin.skill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.galajus.brokenpediabackend.admin.skill.model.AdminSkillBasic;

import java.util.List;

public interface AdminSkillBasicRepository extends JpaRepository<AdminSkillBasic, Long> {

    List<AdminSkillBasic> findAllByClassSkillId(Long id);

}
