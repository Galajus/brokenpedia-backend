package pl.galajus.brokenpediabackend.admin.items.legendary.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.galajus.brokenpediabackend.admin.items.legendary.model.AdminItemSetCustomEffect;
import pl.galajus.brokenpediabackend.admin.items.legendary.model.AdminItemSetPsychoEffect;
import pl.galajus.brokenpediabackend.user.common.model.Profession;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminItemSetDto {

    private Long id;
    private String name;
    private Profession requiredClass;
    private List<AdminItemSetPsychoEffect> psychoEffects;
    private List<AdminItemSetCustomEffect> customEffects;
}
