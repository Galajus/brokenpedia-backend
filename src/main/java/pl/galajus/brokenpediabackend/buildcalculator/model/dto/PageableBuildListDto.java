package pl.galajus.brokenpediabackend.buildcalculator.model.dto;


import org.springframework.data.domain.Page;

public record PageableBuildListDto(Page<BuildListDto> pageableBuilds) {

}
