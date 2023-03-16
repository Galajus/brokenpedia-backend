package pl.galajus.brokenpediabackend.buildcalculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.galajus.brokenpediabackend.buildcalculator.model.SkillBasic;

import java.util.List;

public interface SkillBasicRepository extends JpaRepository<SkillBasic, Long> {

    List<SkillBasic> findAllByClassSkillId(Long id);

}
