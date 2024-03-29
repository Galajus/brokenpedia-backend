package pl.galajus.brokenpediabackend.user.build.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import pl.galajus.brokenpediabackend.user.skill.model.ClassSkill;
import pl.galajus.brokenpediabackend.user.skill.model.DefaultStatistic;
import pl.galajus.brokenpediabackend.user.skill.model.SkillCost;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class InitBuildCalculator {
    private List<ClassSkill> classSkills;
    private List<DefaultStatistic> defaultStatistics;
    private List<SkillCost> skillCosts;
}
