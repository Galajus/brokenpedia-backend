package pl.galajus.brokenpediabackend.user.build.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.galajus.brokenpediabackend.user.common.model.Profession;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BuildListDto {

    private Long id;
    private String buildName;
    private String buildAuthor;
    private String shortDescription;
    private Boolean hidden;
    private Boolean pvpBuild;
    private Profession profession;
    private Integer level;
    private Long likes;
}
