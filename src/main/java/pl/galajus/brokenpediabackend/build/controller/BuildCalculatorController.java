package pl.galajus.brokenpediabackend.build.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.galajus.brokenpediabackend.build.exception.BuildValidationException;
import pl.galajus.brokenpediabackend.build.model.Build;
import pl.galajus.brokenpediabackend.build.model.dto.InitBuildCalculator;
import pl.galajus.brokenpediabackend.build.model.dto.PageableBuildListDto;
import pl.galajus.brokenpediabackend.build.service.BuildService;
import pl.galajus.brokenpediabackend.common.model.Profession;
import pl.galajus.brokenpediabackend.skill.service.ClassSkillService;
import pl.galajus.brokenpediabackend.skill.service.DefaultStatisticService;
import pl.galajus.brokenpediabackend.skill.service.SkillCostService;

@RestController
@RequiredArgsConstructor
public class BuildCalculatorController {

    private final ClassSkillService classSkillService;
    private final DefaultStatisticService defaultStatisticService;
    private final SkillCostService skillCostService;
    private final BuildService buildService;

    @GetMapping("/builds/initData")
    public InitBuildCalculator getInitData() {
        return InitBuildCalculator.builder()
                .classSkills(classSkillService.getAllWithBasicsAndEffects())
                .defaultStatistics(defaultStatisticService.getAll())
                .skillCosts(skillCostService.getAll())
                .build();
    }

    @GetMapping("/builds/{id}")
    public Build getBuildByIdWithoutAccount(@PathVariable Long id) {
        Build build = buildService.getBuildById(id);
        if (build.getHidden()) {
            throw new BuildValidationException("HIDDEN BUILD ACCESS DENIED");
        }
        return build;
    }



    @GetMapping("/builds/by-profession")
    public PageableBuildListDto getBuildsByProfession(
            @RequestParam Profession profession,
            @RequestParam(required = false, defaultValue = "0") Long page) {
        return buildService.getBuildsByProfession(profession, page);
    }

    @GetMapping("/builds/by-level")
    public PageableBuildListDto getBuildsBetweenLevels(@RequestParam(required = false, defaultValue = "200") Integer less,
                                                       @RequestParam(required = false, defaultValue = "0") Integer greater,
                                                       @RequestParam(required = false, defaultValue = "0") Long page) {
        return buildService.getBuildsByLevels(less, greater, page);
    }

    @GetMapping("/builds/by-pvp")
    public PageableBuildListDto getBuildsByIsPvp(
            @RequestParam Boolean pvp,
            @RequestParam(required = false, defaultValue = "0") Long page) {
        return buildService.getBuildsByIsPvp(pvp, page);
    }


}
