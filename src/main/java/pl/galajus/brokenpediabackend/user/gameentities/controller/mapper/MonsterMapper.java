package pl.galajus.brokenpediabackend.user.gameentities.controller.mapper;

import pl.galajus.brokenpediabackend.user.gameentities.model.Monster;
import pl.galajus.brokenpediabackend.user.gameentities.model.dto.MonsterDto;
import pl.galajus.brokenpediabackend.user.items.legendary.controller.mapper.LegendaryItemMapper;

import java.util.List;

public class MonsterMapper {

    public static List<MonsterDto> mapMonstersToMonstersDto(List<Monster> monsters) {
        return monsters.stream().map(MonsterMapper::mapMonsterToMonsterDto)
                .toList();
    }

    public static MonsterDto mapMonsterToMonsterDto(Monster m) {
        return MonsterDto.builder()
                .id(m.getId())
                .name(m.getName())
                .type(m.getType())
                .level(m.getLevel())
                .legendaryDrops(LegendaryItemMapper.mapLegendaryItemsToLegendaryItemsDto(m.getLegendaryDrops()))
                .build();
    }

}
