package pl.galajus.brokenpediabackend.user.items.legendary.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.galajus.brokenpediabackend.user.common.model.Profession;
import pl.galajus.brokenpediabackend.user.items.legendary.model.ItemSetCustomEffect;
import pl.galajus.brokenpediabackend.user.items.legendary.model.ItemSetPsychoEffect;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemSetDto {

    private Long id;
    private String name;
    private Profession requiredClass;
    private List<LegendaryItemDto> setLegendaryItems;
    private List<ItemSetPsychoEffect> psychoEffects;
    private List<ItemSetCustomEffect> customEffects;

}
