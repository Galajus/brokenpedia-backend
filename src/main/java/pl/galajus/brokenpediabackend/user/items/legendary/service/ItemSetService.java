package pl.galajus.brokenpediabackend.user.items.legendary.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.galajus.brokenpediabackend.user.items.legendary.model.ItemSet;
import pl.galajus.brokenpediabackend.user.items.legendary.repository.ItemSetRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemSetService {

    private final ItemSetRepository itemSetRepository;

    @Transactional
    public List<ItemSet> getAll() {
        List<ItemSet> itemSets = itemSetRepository.findAllWithLegendaryItems();
        itemSetRepository.findAllWithPsychoEffects();
        itemSetRepository.findAllWithCustomEffects();
        return itemSets;
    }

}
