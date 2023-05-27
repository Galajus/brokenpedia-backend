package pl.galajus.brokenpediabackend.user.skill.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.galajus.brokenpediabackend.user.skill.model.SkillCost;
import pl.galajus.brokenpediabackend.user.skill.repository.SkillCostRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillCostService {

    private final SkillCostRepository skillCostRepository;

    public List<SkillCost> getAll() {
        return skillCostRepository.findAll();
    }

}
