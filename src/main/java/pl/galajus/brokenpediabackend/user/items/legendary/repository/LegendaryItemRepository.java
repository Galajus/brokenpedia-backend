package pl.galajus.brokenpediabackend.user.items.legendary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.galajus.brokenpediabackend.user.items.legendary.model.ItemType;
import pl.galajus.brokenpediabackend.user.items.legendary.model.LegendaryItem;

import java.util.List;

public interface LegendaryItemRepository extends JpaRepository<LegendaryItem, Long> {
    List<LegendaryItem> findByType(ItemType itemType);

}
