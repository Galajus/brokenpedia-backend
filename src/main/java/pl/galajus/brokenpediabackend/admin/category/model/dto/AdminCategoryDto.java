package pl.galajus.brokenpediabackend.admin.category.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AdminCategoryDto {
    private Long id;
    private String categoryName;
    private String categorySlug;
}
