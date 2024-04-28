package pl.galajus.brokenpediabackend.admin.skill.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.galajus.brokenpediabackend.admin.skill.repository.AdminSkillPsychoEffectRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminSkillPsychoEffectService {

    private final AdminSkillPsychoEffectRepository adminSkillPsychoEffectRepository;

    @Transactional
    public void deleteAllWithIds(List<Long>ids) {
        adminSkillPsychoEffectRepository.deleteAllByIdIn(ids);
    }

}
