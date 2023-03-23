package pl.galajus.brokenpediabackend.buildcalculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.galajus.brokenpediabackend.buildcalculator.model.Build;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BuildRepository extends JpaRepository<Build, Long> {

    @Query("select b from Build b join fetch b.buildDetails where b.ownerUuid = ?1")
    List<Build> findBuildByOwnerUuid(UUID uuid);

    @Query("select b from Build b " +
            "join fetch b.buildDetails bd " +
            "left join fetch b.liking bl " +
            "join fetch bd.skillStatData bss " +
            "where b.id = ?1")
    Optional<Build> findByIdWithJoins(Long id);

    @Query("select b from Build b where b.id = ?1")
    Optional<Build> findById(Long id);

}