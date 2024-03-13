package pl.galajus.brokenpediabackend.user.items.legendary.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.galajus.brokenpediabackend.user.items.legendary.controller.mapper.ItemSetMapper;
import pl.galajus.brokenpediabackend.user.items.legendary.model.dto.ItemSetDto;
import pl.galajus.brokenpediabackend.user.items.legendary.service.ItemSetService;

import java.util.List;

@RestController
@RequestMapping("/items/set")
@RequiredArgsConstructor
public class ItemSetController {

    private final ItemSetService itemSetService;

    @GetMapping
    public List<ItemSetDto> getAll() {
        return ItemSetMapper
                .mapItemSetsToItemSetsDto(itemSetService.getAll());
    }

}
