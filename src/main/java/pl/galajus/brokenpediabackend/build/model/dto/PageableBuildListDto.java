package pl.galajus.brokenpediabackend.build.model.dto;


import org.springframework.data.domain.Page;

public record PageableBuildListDto(Page<BuildListDto> pageableBuilds) {

}
