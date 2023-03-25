package pl.galajus.brokenpediabackend.buildcalculator.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.galajus.brokenpediabackend.buildcalculator.model.Build;
import pl.galajus.brokenpediabackend.common.model.Profession;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BuildRepository extends JpaRepository<Build, Long> {

    @Query("select b from Build b join fetch b.buildDetails left join fetch b.liking where b.ownerUuid = ?1")
    List<Build> findBuildByOwnerUuid(UUID uuid);

    @Query("select b from Build b " +
            "join fetch b.buildDetails bd " +
            "left join fetch b.liking bl " +
            "join fetch bd.skillStatData bss " +
            "where b.id = ?1")
    Optional<Build> findByIdWithJoins(Long id);

    @Query("select b from Build b where b.id = ?1")
    Optional<Build> findById(Long id);

    Page<Build> findByPvpBuildIsTrue(Pageable pageable);

    /* jawny join upraszczający duże zapytania
    @Query(
            value = "select b from Build b join b.buildDetails bd join fetch b.buildDetails fbd where bd.profession = ?1",
            countQuery = "select count (b) from Build b join b.buildDetails bd where bd.profession = ?1"
    )*/
    @Query(
            value = "select b from Build b join fetch b.buildDetails bd where b.buildDetails.profession = ?1 and b.hidden = false",
            countQuery = "select count (b) from Build b left join b.buildDetails bd where b.buildDetails.profession = ?1  and b.hidden = false"
    )
    Page<Build> findByBuildDetailsProfession(Profession profession, Pageable pageable);

    @Query(
            value = "select b from Build b join fetch b.buildDetails where b.pvpBuild = ?1  and b.hidden = false",
            countQuery = "select count (b) from Build b left join b.buildDetails where b.pvpBuild = ?1")
    Page<Build> findByPvpBuild(Boolean pvpBuild, Pageable pageable);

    @Query(
            value = "select b from Build b join fetch b.buildDetails where b.buildDetails.level <= ?1 and b.buildDetails.level >= ?2  and b.hidden = false",
            countQuery = "select count (b) from Build b left join b.buildDetails where b.buildDetails.level <= ?1 and b.buildDetails.level >= ?2  and b.hidden = false"
    )
    Page<Build> findByBuildDetailsLevelIsLessThanEqualAndBuildDetailsLevelGreaterThanEqual(Integer less, Integer greater, Pageable pageable);
}