package pl.galajus.brokenpediabackend.admin.skill.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.galajus.brokenpediabackend.admin.skill.model.AdminSkillCost;
import pl.galajus.brokenpediabackend.admin.skill.repository.AdminSkillCostRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminSkillCostService {

    private final AdminSkillCostRepository adminSkillCostRepository;

    public List<AdminSkillCost> getAll() {
        return adminSkillCostRepository.findAll();
    }

}
