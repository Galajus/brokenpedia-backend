package pl.galajus.brokenpediabackend.buildcalculator.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.galajus.brokenpediabackend.buildcalculator.model.dto.InitBuildCalculator;
import pl.galajus.brokenpediabackend.buildcalculator.service.ClassSkillService;
import pl.galajus.brokenpediabackend.buildcalculator.service.DefaultStatisticService;
import pl.galajus.brokenpediabackend.buildcalculator.service.SkillCostService;

@RestController
@RequestMapping("/builds")
@RequiredArgsConstructor
public class BuildCalculatorController {

    private final ClassSkillService classSkillService;
    private final DefaultStatisticService defaultStatisticService;
    private final SkillCostService skillCostService;

    @GetMapping("/initData")
    public InitBuildCalculator getInitData() {
        return InitBuildCalculator.builder()
                .classSkills(classSkillService.getAll())
                .defaultStatistics(defaultStatisticService.getAll())
                .skillCosts(skillCostService.getAll())
                .build();
    }

}
