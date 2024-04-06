package pl.galajus.brokenpediabackend.admin.items.legendary.controller.mapper;

import pl.galajus.brokenpediabackend.admin.items.legendary.model.AdminLegendaryItem;
import pl.galajus.brokenpediabackend.admin.items.legendary.model.dto.AdminLegendaryItemDto;

import java.util.List;

public class AdminLegendaryItemDtoMapper {

    public static List<AdminLegendaryItemDto> mapAdminLegendaryItemsToAdminLegendaryItemsDto(List<AdminLegendaryItem> adminLegendaryItems) {
        return adminLegendaryItems.stream()
                .map(AdminLegendaryItemDtoMapper::mapAdminLegendaryItemToAdminLegendaryItemDto)
                .toList();
    }

    public static AdminLegendaryItemDto mapAdminLegendaryItemToAdminLegendaryItemDto(AdminLegendaryItem i) {
        return AdminLegendaryItemDto.builder()
                .id(i.getId())
                .name(i.getName())
                .type(i.getType())
                .family(i.getFamily())
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

    public static AdminLegendaryItemDto mapAdminLegendaryItemToAdminLegendaryItemWithSetsDto(AdminLegendaryItem i) {
        AdminLegendaryItemDto adminLegendaryItemDto = mapAdminLegendaryItemToAdminLegendaryItemDto(i);
        adminLegendaryItemDto.setItemSet(AdminItemSetDtoMapper.mapAdminItemSetToAdminSetAloneDto(i.getItemSet()));
        return adminLegendaryItemDto;
    }

    public static AdminLegendaryItemDto mapAdminLegendaryItemToAdminLegendaryItemWithSetsAndMonstersDto(AdminLegendaryItem i) {
        AdminLegendaryItemDto adminLegendaryItemDto = mapAdminLegendaryItemToAdminLegendaryItemDto(i);
        adminLegendaryItemDto.setDroppingMonsters(i.getDroppingMonsters());
        adminLegendaryItemDto.setItemSet(AdminItemSetDtoMapper.mapAdminItemSetToAdminSetAloneDto(i.getItemSet()));
        return adminLegendaryItemDto;
    }

}
