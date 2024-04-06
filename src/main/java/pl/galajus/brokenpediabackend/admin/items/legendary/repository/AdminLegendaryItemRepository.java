package pl.galajus.brokenpediabackend.admin.items.legendary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.galajus.brokenpediabackend.admin.items.legendary.model.AdminLegendaryItem;

import java.util.List;
import java.util.Optional;

public interface AdminLegendaryItemRepository extends JpaRepository<AdminLegendaryItem, Long> {


    @Query(value = "select i from AdminLegendaryItem i " +
            "left join fetch i.droppingMonsters m " +
            "where m.id = ?1",
            countQuery = "select count (i) " +
                    "from AdminLegendaryItem i " +
                    "left join i.droppingMonsters " +
                    "where i.id = ?1")
    List<AdminLegendaryItem> findByDroppingMonsterId(Long id); //TODO check if count query needed

    @Query(value = "select i from AdminLegendaryItem i " +
            "left join fetch i.droppingMonsters " +
            "where i.id = ?1")
    Optional<AdminLegendaryItem> findByIdWithDroppingMonsters(Long id);

    @Query(value = "select i from AdminLegendaryItem i " +
            "left join fetch i.itemSet " +
            "where i.id = ?1")
    Optional<AdminLegendaryItem> findByIdWithItemSetMonsters(Long id);
}
