package pl.galajus.brokenpediabackend.user.post.service.mapper;

import pl.galajus.brokenpediabackend.user.category.model.Category;
import pl.galajus.brokenpediabackend.user.category.model.dto.CategoryDto;
import pl.galajus.brokenpediabackend.user.profile.model.PublicProfile;
import pl.galajus.brokenpediabackend.user.profile.model.dto.PublicProfileDto;
import pl.galajus.brokenpediabackend.user.post.model.FrontPost;
import pl.galajus.brokenpediabackend.user.post.model.Post;
import pl.galajus.brokenpediabackend.user.post.model.dto.MainPagePostDto;

import java.util.List;

public class PostsWithCategoryMapper {

    @Deprecated
    public static List<MainPagePostDto> mapPostToMainPagePostsWithCategories(List<Post> posts) {
        return posts.stream().map(post ->
                MainPagePostDto.builder()
                        .title(post.getTitle())
                        .image(post.getImage())
                        .description(post.getDescription())
                        .slug(post.getSlug())
                        .author(mapPublicProfileToPublicProfileDto(post.getAuthor()))
                        .creationDate(post.getCreationDate())
                        .categories(mapPostCategoriesToPostCategoryDto(post.getCategories()))
                        .build()).toList();
    }

    public static List<MainPagePostDto> mapFrontPostToMainPagePostsWithCategories(List<FrontPost> posts) {
        return posts.stream().map(post ->
                MainPagePostDto.builder()
                        .title(post.getTitle())
                        .image(post.getImage())
                        .description(post.getDescription())
                        .slug(post.getSlug())
                        .author(mapPublicProfileToPublicProfileDto(post.getAuthor()))
                        .creationDate(post.getCreationDate())
                        .categories(mapPostCategoriesToPostCategoryDto(post.getCategories()))
                        .build()).toList();
    }

    private static List<CategoryDto> mapPostCategoriesToPostCategoryDto(List<Category> categories) {
        return categories.stream().map(cat ->
                CategoryDto.builder()
                        .id(cat.getId())
                        .categoryName(cat.getCategoryName())
                        .categorySlug(cat.getCategorySlug())
                        .build()).toList();
    }

    private static PublicProfileDto mapPublicProfileToPublicProfileDto(PublicProfile publicProfile) {
        return PublicProfileDto.builder()
                .uuid(publicProfile.getUuid())
                .nickname(publicProfile.getNickname())
                .build();
    }

    /*private static List<CategoryDto> mapPostCategoriesToPostCategoryDto(Post post, List<Category> categories) {
     *//* categories.stream().filter(category -> category.getId() == post.)*//*
        return null;
    }*/

}
