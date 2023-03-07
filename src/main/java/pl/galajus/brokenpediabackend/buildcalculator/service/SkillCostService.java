package pl.galajus.brokenpediabackend.buildcalculator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.galajus.brokenpediabackend.buildcalculator.model.SkillCost;
import pl.galajus.brokenpediabackend.buildcalculator.repository.SkillCostRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillCostService {

    private final SkillCostRepository skillCostRepository;

    public List<SkillCost> getAll() {
        return skillCostRepository.findAll();
    }

}
