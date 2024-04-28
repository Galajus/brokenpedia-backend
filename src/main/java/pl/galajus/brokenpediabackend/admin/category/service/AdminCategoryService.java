package pl.galajus.brokenpediabackend.admin.category.service;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import pl.galajus.brokenpediabackend.admin.category.model.AdminCategory;
import pl.galajus.brokenpediabackend.admin.category.model.dto.AdminCategoryDto;
import pl.galajus.brokenpediabackend.admin.category.repository.AdminCategoryRepository;
import pl.galajus.brokenpediabackend.admin.common.utils.SlugifyUtils;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminCategoryService {

    private final AdminCategoryRepository adminCategoryRepository;

    public AdminCategory createCategory(AdminCategoryDto category) {
        return adminCategoryRepository.save(AdminCategory.builder()
                .categoryName(category.getCategoryName())
                .categorySlug(SlugifyUtils.slugifySlug(category.getCategoryName()))
                .build());
    }

    @Transactional
    public AdminCategory updateCategory(AdminCategoryDto category) {
        AdminCategory cat = adminCategoryRepository.findById(category.getId()).orElseThrow();
        cat.setCategoryName(category.getCategoryName());
        cat.setCategorySlug(SlugifyUtils.slugifySlug(category.getCategoryName()));
        return adminCategoryRepository.save(cat);
    }

    public List<AdminCategory> getAll() {
        return adminCategoryRepository.findAll();
    }

    public List<AdminCategory> getCategoriesByIds(List<Long> categoriesIds) {
        return adminCategoryRepository.findAllByIdIn(categoriesIds);
    }

    @Transactional
    public void deleteCategory(Long id) {
        AdminCategory adminCategory = adminCategoryRepository.findById(id).orElseThrow();
        adminCategoryRepository.deleteCategoryFromPosts(id);
        adminCategoryRepository.delete(adminCategory);
    }

    public AdminCategory getById(Long id) {
        return adminCategoryRepository.findById(id).orElseThrow();
    }
}
