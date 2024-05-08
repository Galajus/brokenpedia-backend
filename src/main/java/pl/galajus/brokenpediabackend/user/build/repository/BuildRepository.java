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

    @Query(
            value = "select b from Build b " +
                    "join fetch b.buildDetails " +
                    "join fetch b.profile " +
                    "where b.buildDetails.level <= ?1 and " +
                    "b.buildDetails.level >= ?2 and " +
                    "b.pvpBuild = ?3 and " +
                    "b.buildDetails.profession in ?4 and " +
                    "size(b.liking) >= ?5 and " +
                    "b.profile.uuid = ?6",
            countQuery = "select count (b) from Build b " +
                    "left join b.buildDetails " +
                    "left join b.profile " +
                    "where b.buildDetails.level <= ?1 and " +
                    "b.buildDetails.level >= ?2 and " +
                    "b.pvpBuild = ?3 and " +
                    "b.buildDetails.profession in ?4 and " +
                    "size(b.liking) >= ?5 and " +
                    "b.profile.uuid = ?6"
    )
    Page<Build> findByProfileBuildFiltered(Integer levelLess, Integer levelGreater, Boolean isPvp, List<Profession> profession, Long likes, UUID authorUuid, Pageable pageable);

    @Query(
            value = "select b from Build b " +
                    "join fetch b.buildDetails " +
                    "join fetch b.profile " +
                    "join b.liking l " +
                    "where b.buildDetails.level <= ?1 and " +
                    "b.buildDetails.level >= ?2 and " +
                    "b.pvpBuild = ?3 and " +
                    "b.buildDetails.profession in ?4 and " +
                    "size(b.liking) >= ?5 and " +
                    "l.likerUuid = ?6",
            countQuery = "select count (b) from Build b " +
                    "left join b.buildDetails " +
                    "left join b.profile " +
                    "left join b.liking l " +
                    "where b.buildDetails.level <= ?1 and " +
                    "b.buildDetails.level >= ?2 and " +
                    "b.pvpBuild = ?3 and " +
                    "b.buildDetails.profession in ?4 and " +
                    "size(b.liking) >= ?5 and " +
                    "l.likerUuid = ?6"
    )
    Page<Build> findByProfileLikedBuildFiltered(Integer levelLess, Integer levelGreater, Boolean isPvp, List<Profession> profession, Long likes, UUID authorUuid, Pageable pageable);


    @Query("select count(b) from Build b where b.profile.uuid = ?1")
    Long countByProfileUuid(UUID uuid);
}