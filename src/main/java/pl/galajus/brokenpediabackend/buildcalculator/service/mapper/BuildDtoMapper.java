package pl.galajus.brokenpediabackend.buildcalculator.service.mapper;

import pl.galajus.brokenpediabackend.buildcalculator.model.Build;
import pl.galajus.brokenpediabackend.buildcalculator.model.BuildLiker;
import pl.galajus.brokenpediabackend.buildcalculator.model.dto.BuildListDto;

import java.util.List;
import java.util.Objects;

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
                        .likes((long) build.getLiking().size())
                        .build())
                .toList();
    }

    public static List<BuildListDto> mapToBuildListDtoWithLikers(List<Build> builds, List<BuildLiker> likers) {
        return builds.stream().map(build -> BuildListDto.builder()
                        .id(build.getId())
                        .buildName(build.getBuildName())
                        .shortDescription(build.getShortDescription())
                        .pvpBuild(build.getPvpBuild())
                        .hidden(build.getHidden())
                        .profession(build.getBuildDetails().getProfession())
                        .level(build.getBuildDetails().getLevel())
                        .buildAuthor(build.getProfile().getNickname())
                        .likes(likers.stream()
                                .filter(liker -> Objects.equals(liker.getBuildId(), build.getId()))
                                .count())
                        .build())
                .toList();
    }

}
