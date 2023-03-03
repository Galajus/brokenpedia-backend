package pl.galajus.brokenpediabackend.buildcalculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.galajus.brokenpediabackend.buildcalculator.model.ClassSkill;

public interface ClassSkillRepository extends JpaRepository<ClassSkill, Long> {
}
