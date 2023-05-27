package pl.galajus.brokenpediabackend.user.skill.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.galajus.brokenpediabackend.user.skill.model.SkillBasic;
import pl.galajus.brokenpediabackend.user.skill.repository.SkillBasicRepository;

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
