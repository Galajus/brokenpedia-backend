package pl.galajus.brokenpediabackend.user.profile.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.galajus.brokenpediabackend.user.profile.exception.ProfileException;
import pl.galajus.brokenpediabackend.user.profile.model.Profile;
import pl.galajus.brokenpediabackend.user.profile.model.dto.ProfileNicknameDto;
import pl.galajus.brokenpediabackend.user.profile.service.ProfileService;

import java.security.Principal;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping("{uuid}")
    public Profile getProfile(@PathVariable String uuid, Principal principal) {
        if (!principal.getName().equals(uuid)) {
            throw new ProfileException("INVALID USER");
        }
        return profileService.getProfile(uuid);
    }

    @PutMapping("/nickname/{uuid}")
    public void updateNickname(@PathVariable String uuid, @RequestBody @Valid ProfileNicknameDto profileNicknameDto, Principal principal) {
        if (!principal.getName().equals(uuid)) {
            throw new ProfileException("INVALID USER");
        }
        if (profileService.nicknameExist(profileNicknameDto.getNickname())) {
            throw new ProfileException("NICKNAME EXIST");
        }
        profileService.updateNickname(uuid, profileNicknameDto.getNickname());
    }
}
