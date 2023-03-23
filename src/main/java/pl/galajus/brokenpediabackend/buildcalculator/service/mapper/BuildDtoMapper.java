package pl.galajus.brokenpediabackend.buildcalculator.service.mapper;

import pl.galajus.brokenpediabackend.buildcalculator.model.Build;
import pl.galajus.brokenpediabackend.buildcalculator.model.dto.BuildListDto;

import java.util.List;

public class BuildDtoMapper {

    public static List<BuildListDto> mapToBuildListDto(List<Build> builds) {
        return builds.stream().map(build -> BuildListDto.builder()
                        .id(build.getId())
                        .buildName(build.getBuildName())
                        .shortDescription(build.getShortDescription())
                        .pvpBuild(build.getPvpBuild())
                        .hidden(build.getHidden())
                        .profession(build.getBuildDetails().getProfession())
                        .level(build.getBuildDetails().getLevel())
                        .build())
                .toList();
    }

}
