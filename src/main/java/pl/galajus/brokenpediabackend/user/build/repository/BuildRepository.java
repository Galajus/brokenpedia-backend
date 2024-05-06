package pl.galajus.brokenpediabackend.user.build.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.galajus.brokenpediabackend.user.build.model.Build;
import pl.galajus.brokenpediabackend.user.common.model.Profession;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BuildRepository extends JpaRepository<Build, Long> {

    @Query("select b from Build b join fetch b.buildDetails left join fetch b.liking join fetch b.profile where b.profile.uuid = ?1")
    List<Build> findBuildByOwnerUuid(UUID uuid);

    @Query("select b from Build b " +
            "join fetch b.buildDetails bd " +
            "left join fetch b.liking bl " +
            "join fetch b.buildDetails.skillStatData bss " +
            "join fetch b.profile bp " +
            "where b.id = ?1")
    Optional<Build> findByIdWithJoins(Long id);

    @Query("select b from Build b " +
            "join fetch b.profile " +
            "where b.id = ?1")
    Optional<Build> findByIdWithAuthor(Long id);

    Page<Build> findByPvpBuildIsTrue(Pageable pageable);

    /* jawny join upraszczający duże zapytania
    @Query(
            value = "select b from Build b join b.buildDetails bd join fetch b.buildDetails fbd where bd.profession = ?1",
            countQuery = "select count (b) from Build b join b.buildDetails bd where bd.profession = ?1"
    )*/
    @Query(
            value = "select b from Build b " +
                    "join fetch b.buildDetails bd " +
                    "where b.buildDetails.profession = ?1 and b.hidden = false",
            countQuery = "select count (b) " +
                    "from Build b left join b.buildDetails bd " +
                    "where b.buildDetails.profession = ?1 and b.hidden = false"
    )
    Page<Build> findByBuildDetailsProfession(Profession profession, Pageable pageable);

    @Query(
            value = "select b from Build b join fetch b.buildDetails where b.pvpBuild = ?1  and b.hidden = false",
            countQuery = "select count (b) from Build b left join b.buildDetails where b.pvpBuild = ?1")
    Page<Build> findByPvpBuild(Boolean pvpBuild, Pageable pageable);

    @Query(
            value = "select b from Build b " +
                    "join fetch b.buildDetails " +
                    "join fetch b.profile " +
                    "where b.buildDetails.level <= ?1 and b.buildDetails.level >= ?2 and b.hidden = false",
            countQuery = "select count (b) from Build b " +
                    "left join b.buildDetails " +
                    "left join b.profile " +
                    "where b.buildDetails.level <= ?1 and b.buildDetails.level >= ?2  and b.hidden = false"
    )
    Page<Build> findByBuildDetailsLevelIsLessThanEqualAndBuildDetailsLevelGreaterThanEqual(Integer less, Integer greater, Pageable pageable);

    @Query(
            value = "select b from Build b " +
                    "join fetch b.buildDetails " +
                    "join fetch b.profile " +
                    "where b.buildDetails.level <= ?1 and " +
                    "b.buildDetails.level >= ?2 and " +
                    "b.pvpBuild = ?3 and " +
                    "b.buildDetails.profession in ?4 and " +
                    "size(b.liking) >= ?5 and " +
                    "b.hidden = false",
            countQuery = "select count (b) from Build b " +
                    "left join b.buildDetails " +
                    "left join b.profile " +
                    "where b.buildDetails.level <= ?1 and " +
                    "b.buildDetails.level >= ?2 and " +
                    "b.pvpBuild = ?3 and " +
                    "b.buildDetails.profession in ?4 and " +
                    "size(b.liking) >= ?5 and " +
                    "b.hidden = false"
    )
    Page<Build> findByBuildFiltered(Integer levelLess, Integer levelGreater, Boolean isPvp, List<Profession> profession, Long likes, Pageable pageable);

    @Query("select count(b) from Build b where b.profile.uuid = ?1")
    Long countByProfileUuid(UUID uuid);

    /* @Query("select new pl.galajus.brokenpediabackend.buildcalculator.model.dto.BuildListDto(" +
            "b.id, b.buildName, b.shortDescription, b.hidden, b.pvpBuild, sum(count(b.liking) + 1), " +
            "b.buildDetails.profession, b.buildDetails.level, " +
            "p.nickname) " +
            "from Build b " +
            "join b.buildDetails bd " +
            "left join b.liking bl " +
            "join Profile p on p.uuid = ?1 " +
            "where b.profile.uuid = ?1")
    List<BuildListDto> findBuildByOwnerUuidAsDto(UUID uuid);*/
}