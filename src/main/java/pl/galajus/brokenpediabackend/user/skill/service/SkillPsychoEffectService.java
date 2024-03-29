package pl.galajus.brokenpediabackend.user.skill.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.galajus.brokenpediabackend.user.skill.repository.SkillPsychoEffectRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillPsychoEffectService {

    private final SkillPsychoEffectRepository skillPsychoEffectRepository;

    @Transactional
    public void deleteAllWithIds(List<Long>ids) {
        skillPsychoEffectRepository.deleteAllByIdIn(ids);
    }

}
