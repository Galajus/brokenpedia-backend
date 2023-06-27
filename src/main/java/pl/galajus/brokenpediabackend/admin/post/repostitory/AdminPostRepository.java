package pl.galajus.brokenpediabackend.admin.post.repostitory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.galajus.brokenpediabackend.admin.post.model.AdminPost;

import java.util.List;
import java.util.Optional;

public interface AdminPostRepository extends JpaRepository<AdminPost, Long> {

    @Query("select p from AdminPost p " +
            "left join fetch p.categories " +
            "join fetch p.author " +
            "where p.id = ?1")
    Optional<AdminPost> findByIdWithCategoriesAndAuthor(Long id);

    @Query(value = "select p from AdminPost p " +
            "left join fetch p.categories " +
            "join fetch p.author " +
            "where p.id in ?1",
            countQuery = "select count (p) " +
                    "from Post p " +
                    "left join p.categories " +
                    "left join p.author " +
                    "where p.id in ?1")
    List<AdminPost> findPostByIdInWithCategories(List<Long> ids, Sort sort); //todo: fetching without post content

    @Query(value = "select p.id from AdminPost p",
            countQuery = "select count (p) " +
                    "from Post p")
    Page<Long> findAllOnlyIds(boolean isPublic, Pageable pageable);

}
