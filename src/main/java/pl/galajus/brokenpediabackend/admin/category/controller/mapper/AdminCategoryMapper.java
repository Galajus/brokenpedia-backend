package pl.galajus.brokenpediabackend.admin.category.controller.mapper;

import pl.galajus.brokenpediabackend.admin.category.model.AdminCategory;
import pl.galajus.brokenpediabackend.admin.category.model.dto.AdminCategoryDto;

import java.util.List;

public class AdminCategoryMapper {

    public static List<AdminCategoryDto> mapCategoriesToCategoriesDto(List<AdminCategory> categories) {
        return categories.stream().map(AdminCategoryMapper::mapAdminCategoryToAdminCategoryDto)
                .toList();
    }

    public static AdminCategoryDto mapAdminCategoryToAdminCategoryDto(AdminCategory category) {
        return AdminCategoryDto.builder()
                .id(category.getId())
                .categoryName(category.getCategoryName())
                .categorySlug(category.getCategorySlug())
                .build();
    }

}
