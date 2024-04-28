package pl.galajus.brokenpediabackend.admin.items.legendary.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.galajus.brokenpediabackend.admin.common.model.AdminProfession;
import pl.galajus.brokenpediabackend.admin.items.legendary.model.AdminItemSetCustomEffect;
import pl.galajus.brokenpediabackend.admin.items.legendary.model.AdminItemSetPsychoEffect;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminItemSetDto {

    private Long id;
    private String name;
    private AdminProfession requiredClass;
    private List<AdminItemSetPsychoEffect> psychoEffects;
    private List<AdminItemSetCustomEffect> customEffects;
}
