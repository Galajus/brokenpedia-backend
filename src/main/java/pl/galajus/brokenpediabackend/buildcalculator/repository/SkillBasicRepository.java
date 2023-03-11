package pl.galajus.brokenpediabackend.buildcalculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.galajus.brokenpediabackend.buildcalculator.model.SkillBasic;

public interface SkillBasicRepository extends JpaRepository<SkillBasic, Long> {
}
