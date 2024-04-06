package pl.galajus.brokenpediabackend.admin.items.legendary.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.galajus.brokenpediabackend.admin.items.legendary.model.AdminLegendaryItem;
import pl.galajus.brokenpediabackend.admin.items.legendary.repository.AdminLegendaryItemRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminLegendaryItemService {

    private final AdminLegendaryItemRepository adminLegendaryItemRepository;

    public List<AdminLegendaryItem> getAll() {
        return adminLegendaryItemRepository.findAll();
    }

    public AdminLegendaryItem create(AdminLegendaryItem adminLegendaryItem) {
        return adminLegendaryItemRepository.save(adminLegendaryItem);
    }

    public AdminLegendaryItem update(AdminLegendaryItem adminLegendaryItem) {
        return adminLegendaryItemRepository.save(adminLegendaryItem);
    }
    @Transactional
    public AdminLegendaryItem getById(Long id) {
        AdminLegendaryItem adminLegendaryItem = adminLegendaryItemRepository.findByIdWithDroppingMonsters(id).orElseThrow();
        adminLegendaryItemRepository.findByIdWithItemSetMonsters(id).orElseThrow();
        return adminLegendaryItem;
    }
    public void deleteById(Long id) {
        adminLegendaryItemRepository.deleteById(id);
    }
}
