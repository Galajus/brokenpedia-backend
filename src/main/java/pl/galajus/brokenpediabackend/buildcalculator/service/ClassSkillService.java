package pl.galajus.brokenpediabackend.buildcalculator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.galajus.brokenpediabackend.buildcalculator.model.ClassSkill;
import pl.galajus.brokenpediabackend.buildcalculator.repository.ClassSkillRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassSkillService {

    private final ClassSkillRepository classSkillRepository;

    public List<ClassSkill> getAll() {
        return classSkillRepository.findAll();
    }

}
