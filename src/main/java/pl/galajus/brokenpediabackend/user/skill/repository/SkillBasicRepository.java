package pl.galajus.brokenpediabackend.user.skill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.galajus.brokenpediabackend.user.skill.model.SkillBasic;

import java.util.List;

public interface SkillBasicRepository extends JpaRepository<SkillBasic, Long> {

    List<SkillBasic> findAllByClassSkillId(Long id);

}
