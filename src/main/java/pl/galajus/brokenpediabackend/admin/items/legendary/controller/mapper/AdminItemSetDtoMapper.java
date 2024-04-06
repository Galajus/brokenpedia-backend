package pl.galajus.brokenpediabackend.admin.items.legendary.controller.mapper;

import jakarta.annotation.Nullable;
import pl.galajus.brokenpediabackend.admin.items.legendary.model.AdminItemSet;
import pl.galajus.brokenpediabackend.admin.items.legendary.model.dto.AdminItemSetAloneDto;
import pl.galajus.brokenpediabackend.admin.items.legendary.model.dto.AdminItemSetDto;

import java.util.List;

public class AdminItemSetDtoMapper {

    public static List<AdminItemSetAloneDto> mapAdminItemSetsToAdminSetsAloneDto(List<AdminItemSet> adminSets) {
        return adminSets.stream()
                .map(AdminItemSetDtoMapper::mapAdminItemSetToAdminSetAloneDto)
                .toList();
    }

    public static AdminItemSetAloneDto mapAdminItemSetToAdminSetAloneDto(@Nullable AdminItemSet adminSet) {
        if (adminSet == null) {
            return null;
        }
        return AdminItemSetAloneDto.builder()
                .id(adminSet.getId())
                .name(adminSet.getName())
                .requiredClass(adminSet.getRequiredClass())
                .build();
    }

    public static List<AdminItemSetDto> mapAdminItemSetsToAdminSetsDto(List<AdminItemSet> adminSets) {
        return adminSets.stream()
                .map(AdminItemSetDtoMapper::mapAdminItemSetToAdminSetDto)
                .toList();
    }

    public static AdminItemSetDto mapAdminItemSetToAdminSetDto(AdminItemSet adminSet) {
        return AdminItemSetDto.builder()
                .id(adminSet.getId())
                .name(adminSet.getName())
                .requiredClass(adminSet.getRequiredClass())
                .customEffects(adminSet.getCustomEffects())
                .psychoEffects(adminSet.getPsychoEffects())
                .build();
    }

}
