package pl.galajus.brokenpediabackend.profile.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.galajus.brokenpediabackend.profile.model.Profile;
import pl.galajus.brokenpediabackend.profile.service.ProfileService;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping("{uuid}")
    public Profile getProfile(@PathVariable String uuid) {
        return profileService.getProfile(uuid);
    }
}
