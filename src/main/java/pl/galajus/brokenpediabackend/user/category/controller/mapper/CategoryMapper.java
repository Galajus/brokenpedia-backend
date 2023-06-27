package pl.galajus.brokenpediabackend.user.category.controller.mapper;

import pl.galajus.brokenpediabackend.user.category.model.Category;
import pl.galajus.brokenpediabackend.user.category.model.dto.CategoryDto;

import java.util.List;

public class CategoryMapper {

    public static List<CategoryDto> mapCategoriesToCategoriesDto(List<Category> categories) {
        return categories.stream().map(category ->
                        CategoryDto.builder()
                                .id(category.getId())
                                .categoryName(category.getCategoryName())
                                .categorySlug(category.getCategorySlug())
                                .build())
                .toList();
    }

}
