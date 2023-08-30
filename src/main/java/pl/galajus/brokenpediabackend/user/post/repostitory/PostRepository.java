package pl.galajus.brokenpediabackend.user.post.repostitory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pl.galajus.brokenpediabackend.user.post.controller.projection.ProjectionPostIdAndSlug;
import pl.galajus.brokenpediabackend.user.post.model.FrontPost;
import pl.galajus.brokenpediabackend.user.post.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    /*@Query(
            value = "select b from Build b " +
                    "join fetch b.buildDetails bd " +
                    "where b.buildDetails.profession = ?1 and b.hidden = false",
            countQuery = "select count (b) " +
                    "from Build b left join b.buildDetails bd " +
                    "where b.buildDetails.profession = ?1 and b.hidden = false"
    )*/
    @Query(value = "select p from Post p " +
            "join fetch p.author " +
            "where p.isPublic = ?1",
            countQuery = "select count (p) " +
                    "from Post p " +
                    "left join p.author " +
                    "where p.isPublic = ?1")
    Page<Post> findAllByIsPublic(boolean isPublic, Pageable pageable);

    @Query(value = "select p from Post p " +
            "left join fetch p.categories " +
            "join fetch p.author " +
            "where p.id in ?1",
            countQuery = "select count (p) " +
                    "from Post p " +
                    "left join p.categories " +
                    "left join p.author " +
                    "where p.id in ?1")
    List<Post> findPostByIdInWithCategories(List<Long> ids, Sort sort);

    @Query(value = "select p from Post p " +
            "left join fetch p.categories " +
            "join fetch p.author " +
            "where p.id in ?1 and " +
            "p.isPublic = true",
            countQuery = "select count (p) " +
                    "from Post p " +
                    "left join p.categories " +
                    "left join p.author " +
                    "where p.id in ?1 and " +
                    "p.isPublic = true")
    List<Post> findPublicPostByIdInWithCategories(List<Long> ids, Sort sort);

    @Query(value = "select p from FrontPost p " +
            "left join fetch p.categories " +
            "join fetch p.author " +
            "where p.id in ?1 and " +
            "p.isPublic = true",
            countQuery = "select count (p) " +
                    "from FrontPost p " +
                    "left join p.categories " +
                    "left join p.author " +
                    "where p.id in ?1 and " +
                    "p.isPublic = true")
    List<FrontPost> findPublicFrontPostByIdInWithCategories(List<Long> ids, Sort sort);

    <T> T findPostById(Long id, Class<T> type);

    @Query(value = "select p.id from Post p " +
            "where p.isPublic = ?1",
            countQuery = "select count (p) " +
                    "from Post p " +
                    "where p.isPublic = ?1")
    Page<Long> findAllByIsPublicOnlyIds(boolean isPublic, Pageable pageable);

    @Query(value = "select p.id from Post p " +
            "inner join p.categories c " +
            "where c.categorySlug = ?1 and " +
            "p.isPublic = ?2")
    Page<Long> findByCategoryAndIsPublicOnlyIds(String category, boolean isPublic, Pageable pageable);

    @Query("select p from Post p " +
            "left join fetch p.categories " +
            "where p.slug = ?1 and p.isPublic = ?2")
    Optional<Post> findPostBySlugAndIsPublic(String slug, Boolean isPublic);

    @Modifying
    @Query("UPDATE Post p SET p.views = p.views + 1 WHERE p.id = ?1")
    void incrementPostViews(Long postId);

    Optional<ProjectionPostIdAndSlug> findFirstByIsPublicAndIdAfterOrderByIdAsc(Boolean isPublic, Long id);
    Optional<ProjectionPostIdAndSlug> findFirstByIsPublicAndIdBeforeOrderByIdDesc(Boolean isPublic, Long id);
}
