package pl.galajus.brokenpediabackend.admin.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import pl.galajus.brokenpediabackend.admin.category.model.AdminCategory;

import java.util.List;

public interface AdminCategoryRepository extends JpaRepository<AdminCategory, Long> {

    List<AdminCategory> findAllByIdIn(List<Long> ids);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "delete from post_category where category_id = ?1")
    void deleteCategoryFromPosts(Long categoryId);

}
