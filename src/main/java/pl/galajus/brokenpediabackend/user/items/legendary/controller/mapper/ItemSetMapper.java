package pl.galajus.brokenpediabackend.user.items.legendary.controller.mapper;

import pl.galajus.brokenpediabackend.user.items.legendary.model.ItemSet;
import pl.galajus.brokenpediabackend.user.items.legendary.model.dto.ItemSetDto;

import java.util.List;

public class ItemSetMapper {

    public static List<ItemSetDto> mapItemSetsToItemSetsDto(List<ItemSet> itemSets) {
        return itemSets.stream()
                .map(ItemSetMapper::mapItemSetToItemSetDto)
                .toList();
    }

    public static ItemSetDto mapItemSetToItemSetDto(ItemSet itemSet) {
        return ItemSetDto.builder()
                .id(itemSet.getId())
                .name(itemSet.getName())
                .requiredClass(itemSet.getRequiredClass())
                .psychoEffects(itemSet.getPsychoEffects())
                .customEffects(itemSet.getCustomEffects())
                .setLegendaryItems(LegendaryItemMapper.mapLegendaryItemsToLegendaryItemsDto(itemSet.getSetLegendaryItems()))
                .build();
    }

}
