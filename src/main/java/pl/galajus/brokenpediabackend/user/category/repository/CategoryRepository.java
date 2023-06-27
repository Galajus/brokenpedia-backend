package pl.galajus.brokenpediabackend.user.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.galajus.brokenpediabackend.user.category.model.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("select c from Category c inner join c.posts posts where posts.id in ?1")
    List<Category> findAllByPostsIdIn(List<Long> ids);

}
