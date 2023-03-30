package pl.galajus.brokenpediabackend.skill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.galajus.brokenpediabackend.skill.model.SkillBasic;

import java.util.List;

public interface SkillBasicRepository extends JpaRepository<SkillBasic, Long> {

    List<SkillBasic> findAllByClassSkillId(Long id);

}
