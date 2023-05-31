package pl.galajus.brokenpediabackend.user.build.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.galajus.brokenpediabackend.user.build.exception.BuildValidationException;
import pl.galajus.brokenpediabackend.user.build.model.Build;
import pl.galajus.brokenpediabackend.user.build.model.BuildDetails;
import pl.galajus.brokenpediabackend.user.build.model.BuildLiker;
import pl.galajus.brokenpediabackend.user.build.model.BuildSkillStatData;
import pl.galajus.brokenpediabackend.user.build.model.dto.BuildListDto;
import pl.galajus.brokenpediabackend.user.build.model.dto.PageableBuildListDto;
import pl.galajus.brokenpediabackend.user.build.repository.BuildLikerRepository;
import pl.galajus.brokenpediabackend.user.build.repository.BuildRepository;
import pl.galajus.brokenpediabackend.user.common.model.Profession;
import pl.galajus.brokenpediabackend.user.common.service.SanitizeService;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static pl.galajus.brokenpediabackend.user.build.service.mapper.BuildDtoMapper.mapToBuildListDto;
import static pl.galajus.brokenpediabackend.user.build.service.mapper.BuildDtoMapper.mapToBuildListDtoWithLikers;

@Service
@RequiredArgsConstructor
public class BuildService {

    private final BuildRepository buildRepository;
    private final BuildLikerRepository buildLikerRepository;
    private static final Integer PAGE_SIZE = 25;

    public Build create(Build build) {
        this.sanitizeBuild(build);
        return buildRepository.save(build);
    }

    public Build update(Build newBuild, Build oldBuild) {
        //MAIN DATA
        oldBuild.setPvpBuild(newBuild.getPvpBuild());
        oldBuild.setBuildName(newBuild.getBuildName());
        oldBuild.setHidden(newBuild.getHidden());
        oldBuild.setDescription(newBuild.getDescription());
        oldBuild.setPvpBuild(newBuild.getPvpBuild());

        //BUILD DETAILS DATA
        BuildDetails oldBuildDetails = oldBuild.getBuildDetails();
        BuildDetails newBuildDetails = newBuild.getBuildDetails();

        oldBuildDetails.setLevel(newBuildDetails.getLevel());
        oldBuildDetails.setProfession(newBuildDetails.getProfession());

        //SKILL STAT DATA
        List<BuildSkillStatData> oldSkillStatData = oldBuildDetails.getSkillStatData();
        List<BuildSkillStatData> newSkillStatData = newBuildDetails.getSkillStatData();
        oldSkillStatData.forEach(ossd -> {
            Integer newLevel = newSkillStatData
                    .stream().filter(nssd -> Objects.equals(nssd.getId(), ossd.getId()))
                    .findFirst()
                    .orElseThrow(() -> new BuildValidationException("CHANGING CLASS ON EXISTING BUILD NOT ALLOWED"))
                    .getLevel();
            ossd.setLevel(newLevel);
        });

        this.sanitizeBuild(oldBuild);
        return buildRepository.save(oldBuild);
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

    public Build getBuildByIdWithAuthor(Long id) {
        return buildRepository.findByIdWithAuthor(id).orElseThrow();
    }

    public Long getAmountOfBuilds(String uuid) {
        return buildRepository.countByProfileUuid(UUID.fromString(uuid));
    }

    @Transactional(readOnly = true)
    public PageableBuildListDto getBuildsByProfession(Profession profession, Long page) {
        PageRequest pageable = PageRequest.of(Math.toIntExact(page), PAGE_SIZE, Sort.by(Sort.Direction.DESC, "buildDetails.level"));
        Page<Build> builds = buildRepository.findByBuildDetailsProfession(profession, pageable);
        List<BuildLiker> likers = getLikers(builds.getContent());
        List<BuildListDto> buildListDtos = mapToBuildListDtoWithLikers(builds.getContent(), likers);
        return new PageableBuildListDto(new PageImpl<>(buildListDtos, pageable, builds.getTotalElements()));
    }

    @Transactional(readOnly = true)
    public PageableBuildListDto getBuildsByLevels(Integer less, Integer greater, Long page) {
        PageRequest pageable = PageRequest.of(Math.toIntExact(page), PAGE_SIZE, Sort.by(Sort.Direction.DESC, "buildDetails.level"));
        Page<Build> builds = buildRepository.findByBuildDetailsLevelIsLessThanEqualAndBuildDetailsLevelGreaterThanEqual(less, greater, pageable);
        List<BuildLiker> likers = getLikers(builds.getContent());
        List<BuildListDto> buildListDtos = mapToBuildListDtoWithLikers(builds.getContent(), likers);
        return new PageableBuildListDto(new PageImpl<>(buildListDtos, pageable, builds.getTotalElements()));
    }

    @Transactional(readOnly = true)
    public PageableBuildListDto getBuildsByIsPvp(Boolean isPvp, Long page) {
        PageRequest pageable = PageRequest.of(Math.toIntExact(page), PAGE_SIZE, Sort.by(Sort.Direction.DESC, "buildDetails.level"));
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

    private void sanitizeBuild(Build build) {
        build.setBuildName(SanitizeService.cleanCompletely(build.getBuildName()));
        build.setShortDescription(SanitizeService.cleanCompletely(build.getShortDescription()));
        build.setDescription(SanitizeService.cleanRelaxed(build.getDescription()));
    }
}
