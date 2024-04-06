package pl.galajus.brokenpediabackend.admin.items.legendary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.galajus.brokenpediabackend.admin.items.legendary.model.AdminItemSet;
import pl.galajus.brokenpediabackend.admin.items.legendary.model.AdminItemSetCustomEffect;
import pl.galajus.brokenpediabackend.admin.items.legendary.model.AdminItemSetPsychoEffect;
import pl.galajus.brokenpediabackend.user.common.model.Profession;

import java.util.List;
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

    @Modifying
    @Query("update AdminItemSet s set " +
            "s.name = :name, " +
            "s.requiredClass = :requiredClass, " +
            "s.psychoEffects = :psychoEffects, " +
            "s.customEffects = :customEffects " +
            "where s.id = :id")
    AdminItemSet update(@Param("id") Long id,
                        @Param("name") String name,
                        @Param("requiredClass") Profession requiredClass,
                        @Param("psychoEffects") List<AdminItemSetPsychoEffect> psychoEffects,
                        @Param("customEffects") List<AdminItemSetCustomEffect> customEffects);
}
