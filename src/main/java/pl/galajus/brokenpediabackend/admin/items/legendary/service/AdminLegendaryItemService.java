package pl.galajus.brokenpediabackend.admin.items.legendary.service;

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
    public AdminLegendaryItem getById(Long id) {
        return adminLegendaryItemRepository.findByIdWithDroppingMonsters(id).orElseThrow();
    }
    public void deleteById(Long id) {
        adminLegendaryItemRepository.deleteById(id);
    }
}
