package pl.galajus.brokenpediabackend.admin.items.legendary.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import pl.galajus.brokenpediabackend.admin.items.legendary.model.AdminLegendaryItem;
import pl.galajus.brokenpediabackend.admin.items.legendary.repository.AdminLegendaryItemRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminLegendaryItemService {

    private final AdminLegendaryItemRepository adminLegendaryItemRepository;
    private final CacheManager cacheManager;

    public List<AdminLegendaryItem> getAll() {
        return adminLegendaryItemRepository.findAll();
    }

    public AdminLegendaryItem create(AdminLegendaryItem adminLegendaryItem) {
        AdminLegendaryItem item = adminLegendaryItemRepository.save(adminLegendaryItem);
        this.clearChangedCaches();
        return item;
    }

    public AdminLegendaryItem update(AdminLegendaryItem adminLegendaryItem) {
        AdminLegendaryItem item = adminLegendaryItemRepository.save(adminLegendaryItem);
        this.clearChangedCaches();
        return item;
    }
    @Transactional
    public AdminLegendaryItem getById(Long id) {
        AdminLegendaryItem adminLegendaryItem = adminLegendaryItemRepository.findByIdWithDroppingMonsters(id).orElseThrow();
        adminLegendaryItemRepository.findByIdWithItemSetMonsters(id).orElseThrow();
        return adminLegendaryItem;
    }

    public void deleteById(Long id) {
        adminLegendaryItemRepository.deleteById(id);
        this.clearChangedCaches();
    }

    private void clearChangedCaches() {
        Cache monstersWithRars = cacheManager.getCache("MonstersWithRars");
        if (monstersWithRars != null) {
            monstersWithRars.clear();
        }
    }
}
