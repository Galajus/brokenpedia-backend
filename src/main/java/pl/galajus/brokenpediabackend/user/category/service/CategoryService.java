package pl.galajus.brokenpediabackend.user.category.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.galajus.brokenpediabackend.user.category.model.Category;
import pl.galajus.brokenpediabackend.user.category.repository.CategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getCategoriesByPostsIds(List<Long> ids) {
        return categoryRepository.findAllByPostsIdIn(ids);
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }
}
