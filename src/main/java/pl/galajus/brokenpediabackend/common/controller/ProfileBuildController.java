package pl.galajus.brokenpediabackend.common.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.galajus.brokenpediabackend.build.exception.BuildValidationException;
import pl.galajus.brokenpediabackend.build.model.Build;
import pl.galajus.brokenpediabackend.build.model.BuildLiker;
import pl.galajus.brokenpediabackend.build.model.dto.BuildListDto;
import pl.galajus.brokenpediabackend.build.service.BuildLikerService;
import pl.galajus.brokenpediabackend.build.service.BuildService;
import pl.galajus.brokenpediabackend.build.service.BuildValidationService;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProfileBuildController {

    private final BuildService buildService;
    private final BuildLikerService buildLikerService;
    private final BuildValidationService buildValidationService;

    @GetMapping("/profile/builds/{id}")
    public Build getBuildById(@PathVariable Long id, Principal principal) {
        Build build = buildService.getBuildById(id);
        if (build.getHidden() && !build.getProfile().getUuid().toString().equals(principal.getName())) {
            throw new BuildValidationException("HIDDEN BUILD ACCESS DENIED");
        }
        return build;
    }

    @PostMapping("/profile/builds")
    public Build createBuild(@RequestBody @Valid Build build, Principal principal) {
        if (buildValidationService.isInValid(build)) {
            throw new BuildValidationException("INVALID BUILD");
        }
        if (buildService.getAmountOfBuilds(principal.getName()) > 200) {
            throw new BuildValidationException("MAX BUILDS REACHED");
        }
        return buildService.save(build);
    }

    @PutMapping("/profile/builds")
    public Build updateBuild(@RequestBody @Valid Build build) {
        if (buildValidationService.isInValid(build)) {
            throw new BuildValidationException("INVALID BUILD");
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

    //TODO REFACTOR
    @PutMapping("/profile/builds/add-liker")
    public BuildLiker addLiker(@RequestBody BuildLiker liker) {
        return buildLikerService.addLike(liker);
    }

}
