package pl.galajus.brokenpediabackend.profile.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.galajus.brokenpediabackend.profile.model.Profile;
import pl.galajus.brokenpediabackend.profile.repository.ProfileRepository;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    public Profile getProfile(String uuid) {
        return profileRepository.findById(java.util.UUID.fromString(uuid)).orElseThrow();
    }

}
