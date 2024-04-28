package pl.galajus.brokenpediabackend.admin.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pl.galajus.brokenpediabackend.admin.category.model.AdminCategory;

import java.util.List;

public interface AdminCategoryRepository extends JpaRepository<AdminCategory, Long> {

    List<AdminCategory> findAllByIdIn(List<Long> ids);

    @Modifying
    @Query(value = "DELETE FROM post_category WHERE category_id = :categoryId", nativeQuery = true)
    void deleteCategoryFromPosts(Long categoryId);

}
