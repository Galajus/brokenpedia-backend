package pl.galajus.brokenpediabackend.user.items.legendary.controller.mapper;

import pl.galajus.brokenpediabackend.user.items.legendary.model.LegendaryItem;
import pl.galajus.brokenpediabackend.user.items.legendary.model.dto.LegendaryItemDto;

import java.util.List;

public class LegendaryItemMapper {

    public static List<LegendaryItemDto> mapLegendaryItemsToLegendaryItemsDto(List<LegendaryItem> legendaryItems) {
        return legendaryItems.stream()
                .map(LegendaryItemMapper::mapLegendaryItemToLegendaryItemDto)
                .toList();
    }

    public static LegendaryItemDto mapLegendaryItemToLegendaryItemDto(LegendaryItem i) {
        return LegendaryItemDto.builder()
                .id(i.getId())
                .name(i.getName())
                .family(i.getFamily())
                .type(i.getType())
                .weight(i.getWeight())
                .rank(i.getRank())
                .capacity(i.getCapacity())
                .value(i.getValue())
                .requiredLevel(i.getRequiredLevel())
                .requiredStrength(i.getRequiredStrength())
                .requiredDexterity(i.getRequiredDexterity())
                .requiredPower(i.getRequiredPower())
                .requiredKnowledge(i.getRequiredKnowledge())
                .damage(i.getDamage())
                .damageType(i.getDamageType())
                .strength(i.getStrength())
                .dexterity(i.getDexterity())
                .power(i.getPower())
                .knowledge(i.getKnowledge())
                .health(i.getHealth())
                .mana(i.getMana())
                .stamina(i.getStamina())
                .armorSlashing(i.getArmorSlashing())
                .armorCrushing(i.getArmorCrushing())
                .armorPiercing(i.getArmorPiercing())
                .mentalResistance(i.getMentalResistance())
                .fireResistance(i.getFireResistance())
                .energyResistance(i.getEnergyResistance())
                .coldResistance(i.getColdResistance())
                .build();
    }

}
