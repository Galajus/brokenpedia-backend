package pl.galajus.brokenpediabackend.user.build.model.dto;


import org.springframework.data.domain.Page;

public record PageableBuildListDto(Page<BuildListDto> pageableBuilds) {

}
