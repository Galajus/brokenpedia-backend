package pl.galajus.brokenpediabackend.user.category.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CategoryDto {
    private Long id;
    private String categoryName;
    private String categorySlug;
}
