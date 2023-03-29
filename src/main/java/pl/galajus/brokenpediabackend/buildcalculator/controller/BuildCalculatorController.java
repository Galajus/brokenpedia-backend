package pl.galajus.brokenpediabackend.buildcalculator.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.galajus.brokenpediabackend.buildcalculator.model.Build;
import pl.galajus.brokenpediabackend.buildcalculator.model.BuildLiker;
import pl.galajus.brokenpediabackend.buildcalculator.model.dto.BuildListDto;
import pl.galajus.brokenpediabackend.buildcalculator.model.dto.InitBuildCalculator;
import pl.galajus.brokenpediabackend.buildcalculator.model.dto.PageableBuildListDto;
import pl.galajus.brokenpediabackend.buildcalculator.service.BuildLikerService;
import pl.galajus.brokenpediabackend.buildcalculator.service.BuildService;
import pl.galajus.brokenpediabackend.buildcalculator.service.BuildValidationService;
import pl.galajus.brokenpediabackend.buildcalculator.service.ClassSkillService;
import pl.galajus.brokenpediabackend.buildcalculator.service.DefaultStatisticService;
import pl.galajus.brokenpediabackend.buildcalculator.service.SkillCostService;
import pl.galajus.brokenpediabackend.common.model.Profession;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BuildCalculatorController {

    private final ClassSkillService classSkillService;
    private final DefaultStatisticService defaultStatisticService;
    private final SkillCostService skillCostService;
    private final BuildService buildService;
    private final BuildLikerService buildLikerService;
    private final BuildValidationService buildValidationService;

    @GetMapping("/builds/initData")
    public InitBuildCalculator getInitData() {
        return InitBuildCalculator.builder()
                .classSkills(classSkillService.getAllWithBasicsAndEffects())
                .defaultStatistics(defaultStatisticService.getAll())
                .skillCosts(skillCostService.getAll())
                .build();
    }

    @GetMapping("/profile/builds/{id}")
    public Build getBuildById(@PathVariable Long id, Principal principal) {
        Build build = buildService.getBuildById(id);
        if (build.getHidden() && !build.getProfile().getUuid().toString().equals(principal.getName())) {
            throw new RuntimeException("HIDDEN BUILD ACCESS DENIED");
        }
        return build;
    }

    @GetMapping("/builds/{id}")
    public Build getBuildByIdWithoutAccount(@PathVariable Long id) {
        Build build = buildService.getBuildById(id);
        if (build.getHidden()) {
            throw new RuntimeException("HIDDEN BUILD ACCESS DENIED");
        }
        return build;
    }

    @PostMapping("/profile/builds")
    public Build createBuild(@RequestBody @Valid Build build, Principal principal) {
        if (buildValidationService.isInValid(build)) {
            throw new RuntimeException("INVALID BUILD");
        }
        if (buildService.getAmountOfBuilds(principal.getName()) > 200) {
            throw new RuntimeException("MAX BUILDS REACHED");
        }
        return buildService.save(build);
    }

    @PutMapping("/profile/builds")
    public Build updateBuild(@RequestBody @Valid Build build) {
        if (buildValidationService.isInValid(build)) {
            throw new RuntimeException("INVALID BUILD");
        }
        return buildService.save(build);
    }

    @DeleteMapping("/profile/builds/{id}")
    public void deleteBuild(@PathVariable Long id) { //TODO AUTHOR CHECK?
        buildService.deleteBuildById(id);
    }

    @GetMapping("/profile/builds/builds-list/{uuid}")
    public List<BuildListDto> getUserBuilds(@PathVariable String uuid) {
        return buildService.getBuildsListByUuid(uuid);
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

    //TODO REFACTOR
    @PutMapping("/profile/builds/add-liker")
    public BuildLiker addLiker(@RequestBody BuildLiker liker) {
        return buildLikerService.addLike(liker);
    }
}
