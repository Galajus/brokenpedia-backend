package pl.galajus.brokenpediabackend.admin.category.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.galajus.brokenpediabackend.admin.category.controller.mapper.AdminCategoryMapper;
import pl.galajus.brokenpediabackend.admin.category.model.AdminCategory;
import pl.galajus.brokenpediabackend.admin.category.model.dto.AdminCategoryDto;
import pl.galajus.brokenpediabackend.admin.category.service.AdminCategoryService;

import java.util.List;

@RestController
@RequestMapping("/admin/categories")
@RequiredArgsConstructor
public class AdminCategoryController {

    private final AdminCategoryService adminCategoryService;

    @GetMapping
    public List<AdminCategoryDto> getAllCategories() {
        List<AdminCategory> categories = adminCategoryService.getAll();
        return AdminCategoryMapper.mapCategoriesToCategoriesDto(categories);
    }

    @GetMapping("/{id}")
    public AdminCategoryDto getCategory(@PathVariable Long id) {
        AdminCategory cat = adminCategoryService.getById(id);
        return AdminCategoryMapper.mapAdminCategoryToAdminCategoryDto(cat);
    }

    @PostMapping
    public AdminCategoryDto createCategory(@RequestBody AdminCategoryDto category) {
        return AdminCategoryMapper.mapAdminCategoryToAdminCategoryDto(adminCategoryService.createCategory(category));
    }

    @PutMapping
    public AdminCategoryDto updateCategory(@RequestBody AdminCategoryDto category) {
        return AdminCategoryMapper.mapAdminCategoryToAdminCategoryDto(adminCategoryService.updateCategory(category));
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        adminCategoryService.deleteCategory(id);
    }

}
