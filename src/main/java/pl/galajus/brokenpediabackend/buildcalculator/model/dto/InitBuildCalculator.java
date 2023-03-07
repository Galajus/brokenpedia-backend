package pl.galajus.brokenpediabackend.buildcalculator.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import pl.galajus.brokenpediabackend.buildcalculator.model.ClassSkill;
import pl.galajus.brokenpediabackend.buildcalculator.model.DefaultStatistic;
import pl.galajus.brokenpediabackend.buildcalculator.model.SkillCost;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class InitBuildCalculator {
    private List<ClassSkill> classSkills;
    private List<DefaultStatistic> defaultStatistics;
    private List<SkillCost> skillCosts;
}
