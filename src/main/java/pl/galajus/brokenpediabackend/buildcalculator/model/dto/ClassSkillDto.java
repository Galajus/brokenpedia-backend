package pl.galajus.brokenpediabackend.buildcalculator.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.galajus.brokenpediabackend.common.model.Profession;

@Getter
@Setter
@Builder
public class ClassSkillDto {

    private Long id;
    private Integer level;
    private Integer maxLevel;
    private Integer minLevel;
    private Integer beginLevel;
    private String name;
    private String image;
    private Profession profession;

}
