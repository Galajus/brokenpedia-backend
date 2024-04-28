package pl.galajus.brokenpediabackend.admin.items.legendary.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.galajus.brokenpediabackend.admin.common.model.AdminProfession;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminItemSetAloneDto {

    private Long id;
    private String name;
    private AdminProfession requiredClass;

}
