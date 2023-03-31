package pl.galajus.brokenpediabackend.build.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.galajus.brokenpediabackend.build.exception.BuildValidationException;
import pl.galajus.brokenpediabackend.build.model.BuildLiker;
import pl.galajus.brokenpediabackend.build.repository.BuildLikerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuildLikerService {

    private final BuildLikerRepository buildLikerRepository;

    public BuildLiker addLike(BuildLiker buildLiker) {
        buildLikerRepository.findByBuildIdAndLikerUuid(buildLiker.getBuildId(), buildLiker.getLikerUuid())
                .ifPresent(like -> {
            throw new BuildValidationException("LIKE EXIST");
        });
        return buildLikerRepository.save(buildLiker);
    }

    public List<BuildLiker> findByBuildsIds(List<Long> ids) {
        return buildLikerRepository.findByBuildIdIn(ids);
    }

}
