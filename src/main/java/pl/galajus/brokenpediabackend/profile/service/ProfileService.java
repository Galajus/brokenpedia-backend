package pl.galajus.brokenpediabackend.profile.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.galajus.brokenpediabackend.profile.model.Profile;
import pl.galajus.brokenpediabackend.profile.repository.ProfileRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    public Profile getProfile(String uuid) {
        return profileRepository.findById(java.util.UUID.fromString(uuid)).orElseThrow();
    }

    public boolean nicknameExist(String nickname) {
        return profileRepository.existsByNickname(nickname);
    }

    @Transactional
    public void updateNickname(String uuid, String nickname) {
        profileRepository.updateNickname(UUID.fromString(uuid), nickname);
    }

}
