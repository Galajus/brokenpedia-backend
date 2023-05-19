package pl.galajus.brokenpediabackend.build.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.galajus.brokenpediabackend.build.model.Build;
import pl.galajus.brokenpediabackend.build.model.BuildLiker;
import pl.galajus.brokenpediabackend.build.model.dto.BuildListDto;
import pl.galajus.brokenpediabackend.build.model.dto.PageableBuildListDto;
import pl.galajus.brokenpediabackend.build.repository.BuildLikerRepository;
import pl.galajus.brokenpediabackend.build.repository.BuildRepository;
import pl.galajus.brokenpediabackend.common.model.Profession;

import java.util.List;
import java.util.UUID;

import static pl.galajus.brokenpediabackend.build.service.mapper.BuildDtoMapper.mapToBuildListDto;
import static pl.galajus.brokenpediabackend.build.service.mapper.BuildDtoMapper.mapToBuildListDtoWithLikers;

@Service
@RequiredArgsConstructor
public class BuildService {

    private final BuildRepository buildRepository;
    private final BuildLikerRepository buildLikerRepository;
    private static final Integer PAGE_SIZE = 25;

    public Build save(Build build) {
        return buildRepository.save(build);
    }

    public List<BuildListDto> getBuildsListByUuid(String uuid) {
        return mapToBuildListDto(buildRepository.findBuildByOwnerUuid(UUID.fromString(uuid)));
    }

    public void deleteBuildById(Long id) {
        List<BuildLiker> likers = buildLikerRepository.findByBuildId(id);
        buildLikerRepository.deleteAll(likers);
        buildRepository.deleteById(id);
    }

    public Build getBuildById(Long id) {
        return buildRepository.findByIdWithJoins(id).orElseThrow();
    }

    public Long getAmountOfBuilds(String uuid) {
        return buildRepository.countByProfileUuid(UUID.fromString(uuid));
    }

    @Transactional(readOnly = true)
    public PageableBuildListDto getBuildsByProfession(Profession profession, Long page) {
        PageRequest pageable = PageRequest.of(Math.toIntExact(page), 25, Sort.by(Sort.Direction.DESC, "buildDetails.level"));
        Page<Build> builds = buildRepository.findByBuildDetailsProfession(profession, pageable);
        List<BuildLiker> likers = getLikers(builds.getContent());
        List<BuildListDto> buildListDtos = mapToBuildListDtoWithLikers(builds.getContent(), likers);
        return new PageableBuildListDto(new PageImpl<>(buildListDtos, pageable, builds.getTotalElements()));
    }

    @Transactional(readOnly = true)
    public PageableBuildListDto getBuildsByLevels(Integer less, Integer greater, Long page) {
        PageRequest pageable = PageRequest.of(Math.toIntExact(page), 25, Sort.by(Sort.Direction.DESC, "buildDetails.level"));
        Page<Build> builds = buildRepository.findByBuildDetailsLevelIsLessThanEqualAndBuildDetailsLevelGreaterThanEqual(less, greater, pageable);
        List<BuildLiker> likers = getLikers(builds.getContent());
        List<BuildListDto> buildListDtos = mapToBuildListDtoWithLikers(builds.getContent(), likers);
        return new PageableBuildListDto(new PageImpl<>(buildListDtos, pageable, builds.getTotalElements()));
    }

    @Transactional(readOnly = true)
    public PageableBuildListDto getBuildsByIsPvp(Boolean isPvp, Long page) {
        PageRequest pageable = PageRequest.of(Math.toIntExact(page), 25, Sort.by(Sort.Direction.DESC, "buildDetails.level"));
        Page<Build> builds = buildRepository.findByPvpBuild(isPvp, pageable);
        List<BuildLiker> likers = getLikers(builds.getContent());
        List<BuildListDto> buildListDtos = mapToBuildListDtoWithLikers(builds.getContent(), likers);
        return new PageableBuildListDto(new PageImpl<>(buildListDtos, pageable, builds.getTotalElements()));
    }

    private List<BuildLiker> getLikers(List<Build> builds) {
        List<Long> ids = builds.stream().mapToLong(Build::getId)
                .boxed()
                .toList();
        return buildLikerRepository.findByBuildIdIn(ids);
    }
}
