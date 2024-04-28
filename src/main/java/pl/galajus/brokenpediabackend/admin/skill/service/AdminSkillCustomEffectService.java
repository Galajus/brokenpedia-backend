package pl.galajus.brokenpediabackend.admin.skill.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.galajus.brokenpediabackend.admin.skill.repository.AdminSkillCustomEffectRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminSkillCustomEffectService {

    private final AdminSkillCustomEffectRepository adminSkillCustomEffectRepository;

    @Transactional
    public void deleteAllWithIds(List<Long> ids) {
        this.adminSkillCustomEffectRepository.deleteAllByIdIn(ids);
    }

}
