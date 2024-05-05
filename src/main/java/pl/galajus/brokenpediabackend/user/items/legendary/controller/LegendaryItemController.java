package pl.galajus.brokenpediabackend.user.items.legendary.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.galajus.brokenpediabackend.user.items.legendary.controller.mapper.LegendaryItemMapper;
import pl.galajus.brokenpediabackend.user.items.legendary.model.EpicDedicatedMod;
import pl.galajus.brokenpediabackend.user.items.legendary.model.ItemFamily;
import pl.galajus.brokenpediabackend.user.items.legendary.model.ItemType;
import pl.galajus.brokenpediabackend.user.items.legendary.model.dto.LegendaryItemDto;
import pl.galajus.brokenpediabackend.user.items.legendary.service.EpicDedicatedModService;
import pl.galajus.brokenpediabackend.user.items.legendary.service.LegendaryItemService;

import java.util.List;

@RestController
@RequestMapping("/items/legendary")
@RequiredArgsConstructor
public class LegendaryItemController {

    private final LegendaryItemService legendaryItemService;
    private final EpicDedicatedModService epicDedicatedModService;

    @GetMapping
    public List<LegendaryItemDto> getAll() {
        return LegendaryItemMapper
                .mapLegendaryItemsToLegendaryItemsDto(legendaryItemService.getAll());
    }

    @GetMapping("/type/{type}")
    public List<LegendaryItemDto> getAllByType(@PathVariable ItemType type) {
        return LegendaryItemMapper
                .mapLegendaryItemsToLegendaryItemsDto(legendaryItemService.getAllByType(type));
    }

    @GetMapping("/family/{family}")
    public List<LegendaryItemDto> getAllByType(@PathVariable ItemFamily family) {
        return LegendaryItemMapper
                .mapLegendaryItemsToLegendaryItemsDto(legendaryItemService.getAllByFamily(family));
    }

    @GetMapping("/id/{id}")
    public LegendaryItemDto getById(@PathVariable Long id) {
        return LegendaryItemMapper.mapLegendaryItemToLegendaryItemDto(legendaryItemService.getById(id));
    }

    @GetMapping("/epics-mods")
    public List<EpicDedicatedMod> getEpicsDedicatedMods() {
        return epicDedicatedModService.getAll();
    }

}
