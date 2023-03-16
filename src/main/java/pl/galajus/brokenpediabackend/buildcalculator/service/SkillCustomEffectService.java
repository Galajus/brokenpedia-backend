package pl.galajus.brokenpediabackend.buildcalculator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.galajus.brokenpediabackend.buildcalculator.repository.SkillCustomEffectRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillCustomEffectService {

    private final SkillCustomEffectRepository skillCustomEffectRepository;

    @Transactional
    public void deleteAllWithIds(List<Long> ids) {
        this.skillCustomEffectRepository.deleteAllByIdIn(ids);
    }

}
