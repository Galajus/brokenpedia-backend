package pl.galajus.brokenpediabackend.user.items.legendary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.galajus.brokenpediabackend.user.items.legendary.model.ItemSet;

import java.util.List;

public interface ItemSetRepository extends JpaRepository<ItemSet, Long> {

    @Query(value = "select s from ItemSet s " +
            "left join fetch s.customEffects")
    List<ItemSet> findAllWithCustomEffects();
    @Query(value = "select s from ItemSet s " +
            "left join fetch s.psychoEffects")
    List<ItemSet> findAllWithPsychoEffects();

    @Query(value = "select s from ItemSet s " +
            "left join fetch s.setLegendaryItems")
    List<ItemSet> findAllWithLegendaryItems();

}
