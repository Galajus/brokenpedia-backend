package pl.galajus.brokenpediabackend.admin.items.legendary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.galajus.brokenpediabackend.admin.items.legendary.model.AdminLegendaryItem;

import java.util.Optional;

public interface AdminLegendaryItemRepository extends JpaRepository<AdminLegendaryItem, Long> {

    @Query(value = "select i from AdminLegendaryItem i " +
            "left join fetch i.droppingMonsters " +
            "where i.id = ?1")
    Optional<AdminLegendaryItem> findByIdWithDroppingMonsters(Long id);

    @Query(value = "select i from AdminLegendaryItem i " +
            "left join fetch i.itemSet " +
            "where i.id = ?1")
    Optional<AdminLegendaryItem> findByIdWithItemSetMonsters(Long id);
}
