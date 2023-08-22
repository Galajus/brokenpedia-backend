package pl.galajus.brokenpediabackend.admin.gameentities.controller.mapper;

import pl.galajus.brokenpediabackend.admin.gameentities.model.AdminMonster;
import pl.galajus.brokenpediabackend.admin.gameentities.model.dto.AdminMonsterWithoutDropsDto;

import java.util.List;

public class AdminMonsterMapper {

    public static List<AdminMonsterWithoutDropsDto> mapAdminMonstersToMonstersWithoutDropsDto(List<AdminMonster> adminMonsters) {
        return adminMonsters.stream().map(AdminMonsterMapper::mapAdminMonsterToMonsterWithoutDropsDto)
                .toList();
    }

    public static AdminMonsterWithoutDropsDto mapAdminMonsterToMonsterWithoutDropsDto(AdminMonster adminMonster) {
        return AdminMonsterWithoutDropsDto.builder()
                .id(adminMonster.getId())
                .name(adminMonster.getName())
                .type(adminMonster.getType())
                .build();
    }

}
