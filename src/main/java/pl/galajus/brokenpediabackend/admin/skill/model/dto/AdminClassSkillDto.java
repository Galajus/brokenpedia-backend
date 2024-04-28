package pl.galajus.brokenpediabackend.admin.skill.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.galajus.brokenpediabackend.admin.common.model.AdminProfession;

@Getter
@Setter
@Builder
public class AdminClassSkillDto {

    private Long id;
    private Integer level;
    private Integer maxLevel;
    private Integer minLevel;
    private Integer beginLevel;
    private String name;
    private String requirements;
    private String formula;
    private String image;
    private AdminProfession profession;

}
