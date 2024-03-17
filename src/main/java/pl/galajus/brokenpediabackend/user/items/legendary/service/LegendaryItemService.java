package pl.galajus.brokenpediabackend.user.items.legendary.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.galajus.brokenpediabackend.user.items.legendary.model.ItemFamily;
import pl.galajus.brokenpediabackend.user.items.legendary.model.ItemType;
import pl.galajus.brokenpediabackend.user.items.legendary.model.LegendaryItem;
import pl.galajus.brokenpediabackend.user.items.legendary.repository.LegendaryItemRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LegendaryItemService {

    private final LegendaryItemRepository legendaryItemRepository;


    public List<LegendaryItem> getAll() {
        return legendaryItemRepository.findAll();
    }
    public List<LegendaryItem> getAllByType(ItemType itemType) {
        return legendaryItemRepository.findByType(itemType);
    }
    public List<LegendaryItem> getAllByFamily(ItemFamily itemFamily) {
        return legendaryItemRepository.findByFamily(itemFamily);
    }
    public LegendaryItem getById(Long id) {
        return legendaryItemRepository.findById(id).orElseThrow();
    }
}
