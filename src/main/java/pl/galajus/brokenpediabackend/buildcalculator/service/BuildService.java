package pl.galajus.brokenpediabackend.buildcalculator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.galajus.brokenpediabackend.buildcalculator.model.Build;
import pl.galajus.brokenpediabackend.buildcalculator.model.dto.BuildListDto;
import pl.galajus.brokenpediabackend.buildcalculator.repository.BuildRepository;

import java.util.List;
import java.util.UUID;

import static pl.galajus.brokenpediabackend.buildcalculator.service.mapper.BuildDtoMapper.mapToBuildListDto;

@Service
@RequiredArgsConstructor
public class BuildService {

    private final BuildRepository buildRepository;

    public Build save(Build build) {
        return buildRepository.save(build);
    }

    public List<BuildListDto> getBuildsListByUuid(String uuid) {
        return mapToBuildListDto(buildRepository.findBuildByOwnerUuid(UUID.fromString(uuid)));
    }

    public void deleteBuildById(Long id) {
        buildRepository.deleteById(id);
    }

    public Build getBuildById(Long id) {
        return buildRepository.findByIdWithJoins(id).orElseThrow();
    }
}
