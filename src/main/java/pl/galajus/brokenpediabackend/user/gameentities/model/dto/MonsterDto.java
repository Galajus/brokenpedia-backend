package pl.galajus.brokenpediabackend.user.gameentities.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.galajus.brokenpediabackend.user.gameentities.model.MonsterType;
import pl.galajus.brokenpediabackend.user.items.legendary.model.dto.LegendaryItemDto;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MonsterDto {
    private Long id;
    private String name;
    private MonsterType type;
    private List<LegendaryItemDto> legendaryDrops; //todo: move to common legendary items dto?
}
