package pl.galajus.brokenpediabackend.user.items.legendary.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.galajus.brokenpediabackend.user.items.legendary.controller.mapper.LegendaryItemMapper;
import pl.galajus.brokenpediabackend.user.items.legendary.model.ItemType;
import pl.galajus.brokenpediabackend.user.items.legendary.model.LegendaryItem;
import pl.galajus.brokenpediabackend.user.items.legendary.model.dto.LegendaryItemDto;
import pl.galajus.brokenpediabackend.user.items.legendary.service.LegendaryItemService;

import java.util.List;

@RestController
@RequestMapping("/items/legendary")
@RequiredArgsConstructor
public class LegendaryItemController {

    private final LegendaryItemService legendaryItemService;

    @GetMapping
    public List<LegendaryItemDto> getAll() {
        return LegendaryItemMapper.mapLegendaryItemsToLegendaryItemsDto(legendaryItemService.getAll());
    }

    @GetMapping("/type/{type}")
    public List<LegendaryItem> getAllByType(@PathVariable ItemType type) {
        return legendaryItemService.getAllByType(type);
    }

    @GetMapping("/id/{id}")
    public LegendaryItem getById(@PathVariable Long id) {
        return legendaryItemService.getById(id);
    }

}
