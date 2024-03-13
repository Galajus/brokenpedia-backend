package pl.galajus.brokenpediabackend.admin.items.legendary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.galajus.brokenpediabackend.admin.items.legendary.model.AdminItemSet;

import java.util.Optional;

public interface AdminItemSetRepository extends JpaRepository<AdminItemSet, Long> {

    @Query(value = "select s from AdminItemSet s " +
            "left join fetch s.customEffects " +
            "where s.id = ?1")
    Optional<AdminItemSet> findByIdWithCustomEffects(Long id);
    @Query(value = "select s from AdminItemSet s " +
            "left join fetch s.psychoEffects " +
            "where s.id = ?1")
    Optional<AdminItemSet> findByIdWithPsychoEffects(Long id);

}
