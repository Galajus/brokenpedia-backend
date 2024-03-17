package pl.galajus.brokenpediabackend.admin.gameentities.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.galajus.brokenpediabackend.admin.gameentities.model.AdminMonsterType;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminMonsterWithoutDropsDto {

    private Long id;
    private String name;
    private AdminMonsterType type;
    private Integer level;
}
