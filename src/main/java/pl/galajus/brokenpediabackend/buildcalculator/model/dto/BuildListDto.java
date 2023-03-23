package pl.galajus.brokenpediabackend.buildcalculator.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.galajus.brokenpediabackend.common.model.Profession;

@Getter
@Setter
@Builder
public class BuildListDto {

    private Long id;
    private String buildName;
    private String shortDescription;
    private Boolean hidden;
    private Boolean pvpBuild;
    private Profession profession;
    private Integer level;

}
