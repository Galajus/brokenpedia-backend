package pl.galajus.brokenpediabackend.buildcalculator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.galajus.brokenpediabackend.buildcalculator.model.BuildLiker;
import pl.galajus.brokenpediabackend.buildcalculator.repository.BuildLikerRepository;

@Service
@RequiredArgsConstructor
public class BuildLikerService {

    private final BuildLikerRepository buildLikerRepository;

    public BuildLiker addLike(BuildLiker buildLiker) {
        buildLikerRepository.findByBuildIdAndLikerUuid(buildLiker.getBuildId(), buildLiker.getLikerUuid())
                .ifPresent(like -> {
            throw new RuntimeException("LIKE EXIST");
        });
        return buildLikerRepository.save(buildLiker);
    }

}
