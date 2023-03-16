package pl.galajus.brokenpediabackend.buildcalculator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.galajus.brokenpediabackend.buildcalculator.model.SkillBasic;
import pl.galajus.brokenpediabackend.buildcalculator.repository.SkillBasicRepository;

@Service
@RequiredArgsConstructor
public class SkillBasicService {

    private final SkillBasicRepository skillBasicRepository;

    public SkillBasic saveSkillBasic(SkillBasic skillBasic) {
        return this.skillBasicRepository.save(skillBasic);
    }

    public void delete(Long id) {
        skillBasicRepository.deleteById(id);
    }
}
