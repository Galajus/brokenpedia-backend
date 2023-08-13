package pl.galajus.brokenpediabackend.user.post.controller.mapper;

import pl.galajus.brokenpediabackend.user.category.model.Category;
import pl.galajus.brokenpediabackend.user.category.model.dto.CategoryDto;
import pl.galajus.brokenpediabackend.user.post.model.Post;
import pl.galajus.brokenpediabackend.user.post.model.dto.SinglePostDto;

import java.util.List;

public class PostMapper {

    public static SinglePostDto mapPostToSinglePostDto(Post post, String nextPostSlug, String previousPostSlug) {
        return SinglePostDto.builder()
                .id(post.getId())
                .creationDate(post.getCreationDate())
                .lastUpdateDate(post.getLastUpdateDate())
                .categories(mapCategoriesToCategoriesDto(post.getCategories()))
                .author(post.getAuthor())
                .slug(post.getSlug())
                .title(post.getTitle())
                .description(post.getDescription())
                .content(post.getContent())
                .image(post.getImage())
                .views(post.getViews())
                .nextPostSlug(nextPostSlug)
                .previousPostSlug(previousPostSlug)
                .build();
    }

    private static List<CategoryDto> mapCategoriesToCategoriesDto(List<Category> categories) {
        return categories.stream().map(cat ->
                        CategoryDto.builder()
                                .id(cat.getId())
                                .categoryName(cat.getCategoryName())
                                .categorySlug(cat.getCategorySlug())
                                .build())
                .toList();
    }
}
