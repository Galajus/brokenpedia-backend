package pl.galajus.brokenpediabackend.user.build.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.galajus.brokenpediabackend.user.build.exception.BuildValidationException;
import pl.galajus.brokenpediabackend.user.build.model.BuildLiker;
import pl.galajus.brokenpediabackend.user.build.repository.BuildLikerRepository;
import pl.galajus.brokenpediabackend.user.build.repository.BuildRepository;
import pl.galajus.brokenpediabackend.user.profile.service.ProfileService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuildLikerService {

    private final BuildLikerRepository buildLikerRepository;
    private final BuildRepository buildRepository;
    private final ProfileService profileService;

    public BuildLiker addLike(BuildLiker buildLiker) {
        profileService.getProfile(buildLiker.getLikerUuid().toString()); //USER EXIST CHECK
        buildRepository.findById(buildLiker.getBuildId()) //BUILD EXIST CHECK
                .orElseThrow(() -> new BuildValidationException("LIKED BUILD NOT EXIST"));
        buildLikerRepository.findByBuildIdAndLikerUuid(buildLiker.getBuildId(), buildLiker.getLikerUuid())
                .ifPresent(like -> {
            throw new BuildValidationException("LIKE EXIST");
        });

        return buildLikerRepository.save(buildLiker);
    }

    public List<BuildLiker> findByBuildsByIds(List<Long> ids) {
        return buildLikerRepository.findByBuildIdIn(ids);
    }

}
