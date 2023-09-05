package pl.galajus.brokenpediabackend.user.build.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.galajus.brokenpediabackend.user.build.exception.BuildValidationException;
import pl.galajus.brokenpediabackend.user.build.model.Build;
import pl.galajus.brokenpediabackend.user.build.model.BuildSkillStatData;
import pl.galajus.brokenpediabackend.user.skill.model.ClassSkill;
import pl.galajus.brokenpediabackend.user.skill.model.SkillCost;
import pl.galajus.brokenpediabackend.user.skill.model.SkillStatType;
import pl.galajus.brokenpediabackend.user.skill.service.ClassSkillService;
import pl.galajus.brokenpediabackend.user.skill.service.SkillCostService;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BuildValidationService {

    private static final int DEFAULT_STAT_SUM = 100;
    private static final int STATS_BY_LEVEL = 4;
    private static final int VALID_LENGTH = 24;
    private final SkillCostService skillCostService;
    private final ClassSkillService classSkillService;

    public boolean isInValid(Build build) {
        Integer level = build.getBuildDetails().getLevel();

        checkIfBuildDataLengthIsValid(build);

        List<BuildSkillStatData> stats = build.getBuildDetails().getSkillStatData().stream()
                .map(buildSkillStatData -> {
                    if (buildSkillStatData.getSkillStatType().equals(SkillStatType.STAT)) {
                        return buildSkillStatData;
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .toList();

        List<BuildSkillStatData> basicsSkills = build.getBuildDetails().getSkillStatData().stream()
                .map(buildSkillStatData -> {
                    if (buildSkillStatData.getSkillStatType().equals(SkillStatType.BASIC_SKILL)) {
                        return buildSkillStatData;
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .toList();

        List<BuildSkillStatData> classSkills = build.getBuildDetails().getSkillStatData().stream()
                .map(buildSkillStatData -> {
                    if (buildSkillStatData.getSkillStatType().equals(SkillStatType.CLASS_SKILL)) {
                        return buildSkillStatData;
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .toList();

        if (spentStatsIsInvalid(level, stats)) {
            throw new BuildValidationException("SPENT STAT POINTS ARE INVALID");
        }
        if (spentSkillPointsIsInvalid(level, classSkills, basicsSkills)) {
            throw new BuildValidationException("SPENT SKILL POINTS ARE INVALID");
        }

        return false;
    }

    private void checkIfBuildDataLengthIsValid(Build build) {
        if (build.getBuildDetails().getSkillStatData().size() != VALID_LENGTH) {
            throw new BuildValidationException("SKILLSTATDATA INVALID LENGTH");
        }
    }

    private boolean spentStatsIsInvalid(int level, List<BuildSkillStatData> stats) {
        Integer levelStatSum = stats.stream()
                .reduce(0, (partSumLevels, stat) -> partSumLevels + stat.getLevel(), Integer::sum);
        levelStatSum -= DEFAULT_STAT_SUM;
        int maxStatAvailable = level * STATS_BY_LEVEL + 1;
        return (maxStatAvailable - levelStatSum) < 0;
    }

    private boolean spentSkillPointsIsInvalid(int level, List<BuildSkillStatData> classSkills, List<BuildSkillStatData> basicSkills) {
        List<SkillCost> skillCosts = skillCostService.getAll();
        List<ClassSkill> classSkillData = classSkillService.getAll();

        int availableSkillPoints = calculateAvailableSkillPoints(level);
        int spentSkillPoints = calculatePointsSpentOnBasicSkills(basicSkills, skillCosts, classSkillData)
                + calculatePointsSpentOnClassSKills(classSkills, skillCosts);
        return availableSkillPoints < spentSkillPoints;
    }

    private int calculatePointsSpentOnClassSKills(List<BuildSkillStatData> classSkills, List<SkillCost> skillCosts) {
        return classSkills.stream().reduce(0, (partPointsSpent, classSkill) -> partPointsSpent + skillCosts.stream()
                        .filter(cost -> Objects.equals(cost.getLevel(), classSkill.getLevel()))
                        .findFirst().orElseThrow()
                        .getSumCost()
                , Integer::sum);
    }

    private int calculatePointsSpentOnBasicSkills(List<BuildSkillStatData> basicSkills, List<SkillCost> skillCosts, List<ClassSkill> classSkillData) {
        return basicSkills.stream().reduce(0, (partPointsSpent, basicSkill) -> {
                    SkillCost skillCost = skillCosts.stream()
                            .filter(cost -> Objects.equals(cost.getLevel(), basicSkill.getLevel()))
                            .findFirst().orElseThrow();
                    ClassSkill skill = classSkillData.stream()
                            .filter(cs -> Objects.equals(cs.getId(), basicSkill.getSkillStatId()))
                            .findFirst().orElseThrow();
                    return skill.getMinLevel() == 0 ? partPointsSpent + skillCost.getSumCost() : partPointsSpent + skillCost.getSumCost() - 1;
                }
                , Integer::sum);
    }

    private int calculateAvailableSkillPoints(int level) {
        return (int) (level * (((double)level + 1) / 2)) - 1;
    }

    //TODO: Validation beginLevel and build level and max available skillLevel

}
