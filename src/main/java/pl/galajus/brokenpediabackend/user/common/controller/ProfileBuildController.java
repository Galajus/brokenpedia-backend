package pl.galajus.brokenpediabackend.user.common.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.galajus.brokenpediabackend.user.build.exception.BuildValidationException;
import pl.galajus.brokenpediabackend.user.build.model.Build;
import pl.galajus.brokenpediabackend.user.build.model.BuildLiker;
import pl.galajus.brokenpediabackend.user.build.model.dto.BuildListDto;
import pl.galajus.brokenpediabackend.user.build.service.BuildLikerService;
import pl.galajus.brokenpediabackend.user.build.service.BuildService;
import pl.galajus.brokenpediabackend.user.build.service.BuildValidationService;
import pl.galajus.brokenpediabackend.user.profile.model.Profile;
import pl.galajus.brokenpediabackend.user.profile.service.ProfileService;
import pl.galajus.brokenpediabackend.user.security.model.UserRole;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProfileBuildController {

    private final ProfileService profileService;
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
        if (!build.getProfile().getUuid().toString().equals(principal.getName())) {
            throw new BuildValidationException("INVALID BUILD AUTHOR");
        }
        if (buildValidationService.isInValid(build)) {
            throw new BuildValidationException("INVALID BUILD");
        }
        if (buildService.getAmountOfBuilds(principal.getName()) > 200) {
            throw new BuildValidationException("MAX BUILDS REACHED");
        }

        return buildService.create(build);
    }

    @PutMapping("/profile/builds")
    public Build updateBuild(@RequestBody @Valid Build newBuild, Principal principal) {
        Build oldBuild = buildService.getBuildById(newBuild.getId());
        if (!oldBuild.getProfile().getUuid().toString().equals(principal.getName())) {
            throw new BuildValidationException("YOU CANT CHANGE NOT YOURS BUILD");
        }
        if (buildValidationService.isInValid(newBuild)) {
            throw new BuildValidationException("INVALID BUILD");
        }
        return buildService.update(newBuild, oldBuild);
    }

    @DeleteMapping("/profile/builds/{id}")
    public void deleteBuild(@PathVariable Long id, Principal principal) {
        Build build = buildService.getBuildByIdWithAuthor(id);
        if (!build.getProfile().getUuid().toString().equals(principal.getName())) {

            Profile profile = profileService.getProfile(principal.getName());
            if (!profile.getAuthorities().contains(UserRole.ROLE_ADMIN)) {
                throw new BuildValidationException("YOU CANT DELETE NOT YOURS BUILD");
            }
        }
        buildService.deleteBuildById(id);
    }

    @GetMapping("/profile/builds/builds-list")
    public List<BuildListDto> getUserBuilds(Principal principal) {
        return buildService.getBuildsListByUuid(principal.getName());
    }

    //TODO REFACTOR
    @PutMapping("/profile/builds/add-liker")
    public BuildLiker addLiker(@RequestBody BuildLiker liker, Principal principal) {
        if (!liker.getLikerUuid().toString().equals(principal.getName())) {
            throw new BuildValidationException("INVALID LIKE AUTHOR");
        }
        return buildLikerService.addLike(liker);
    }

}
