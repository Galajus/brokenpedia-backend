package pl.galajus.brokenpediabackend.admin.items.legendary.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.galajus.brokenpediabackend.admin.items.legendary.controller.mapper.AdminLegendaryItemDtoMapper;
import pl.galajus.brokenpediabackend.admin.items.legendary.model.AdminLegendaryItem;
import pl.galajus.brokenpediabackend.admin.items.legendary.model.dto.AdminLegendaryItemDto;
import pl.galajus.brokenpediabackend.admin.items.legendary.service.AdminLegendaryItemService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/items/legendary")
public class AdminLegendaryItemController {

    private final AdminLegendaryItemService adminLegendaryItemService;

    @GetMapping
    public List<AdminLegendaryItemDto> getAll() {
        return AdminLegendaryItemDtoMapper
                .mapAdminLegendaryItemsToAdminLegendaryItemsDto(adminLegendaryItemService.getAll());
    }

    @GetMapping("/{id}")
    public AdminLegendaryItemDto getById(@PathVariable Long id) {
        return AdminLegendaryItemDtoMapper
                .mapAdminLegendaryItemToAdminLegendaryItemWithSetsAndMonstersDto(adminLegendaryItemService.getById(id));
    }

    @PostMapping
    public AdminLegendaryItemDto createAdminLegendaryItem(@RequestBody AdminLegendaryItem adminLegendaryItem) {
        return AdminLegendaryItemDtoMapper
                .mapAdminLegendaryItemToAdminLegendaryItemDto(adminLegendaryItemService.create(adminLegendaryItem));
    }

    @PutMapping
    public AdminLegendaryItemDto updateAdminLegendaryItem(@RequestBody AdminLegendaryItem adminLegendaryItem) {
        return AdminLegendaryItemDtoMapper
                .mapAdminLegendaryItemToAdminLegendaryItemDto(adminLegendaryItemService.update(adminLegendaryItem));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        adminLegendaryItemService.deleteById(id);
    }

}
