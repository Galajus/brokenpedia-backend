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
import pl.galajus.brokenpediabackend.admin.items.legendary.controller.mapper.AdminItemSetDtoMapper;
import pl.galajus.brokenpediabackend.admin.items.legendary.model.AdminItemSet;
import pl.galajus.brokenpediabackend.admin.items.legendary.model.dto.AdminItemSetAloneDto;
import pl.galajus.brokenpediabackend.admin.items.legendary.model.dto.AdminItemSetDto;
import pl.galajus.brokenpediabackend.admin.items.legendary.service.AdminItemSetService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/items/set")
public class AdminItemSetController {

    private final AdminItemSetService adminItemSetService;

    @GetMapping
    public List<AdminItemSetAloneDto> getAll() {
        return AdminItemSetDtoMapper
                .mapAdminItemSetsToAdminSetsAloneDto(adminItemSetService.getAll());
    }

    @GetMapping("/{id}")
    public AdminItemSetDto getById(@PathVariable Long id) {
        return AdminItemSetDtoMapper
                .mapAdminItemSetToAdminSetDto(adminItemSetService.getById(id));
    }

    @PostMapping
    public AdminItemSetDto createAdminSet(@RequestBody AdminItemSet adminSet) {
        return AdminItemSetDtoMapper
                .mapAdminItemSetToAdminSetDto(adminItemSetService.create(adminSet));
    }

    @PutMapping
    public AdminItemSetDto updateAdminSet(@RequestBody AdminItemSet adminSet) {
        return AdminItemSetDtoMapper
                .mapAdminItemSetToAdminSetDto(adminItemSetService.create(adminSet));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        adminItemSetService.deleteById(id);
    }

}
