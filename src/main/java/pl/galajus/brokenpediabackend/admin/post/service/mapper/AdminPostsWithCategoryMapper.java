package pl.galajus.brokenpediabackend.admin.post.service.mapper;

import pl.galajus.brokenpediabackend.admin.category.model.AdminCategory;
import pl.galajus.brokenpediabackend.admin.category.model.dto.AdminCategoryDto;
import pl.galajus.brokenpediabackend.admin.post.model.AdminPost;
import pl.galajus.brokenpediabackend.admin.post.model.dto.ListAdminPostDto;
import pl.galajus.brokenpediabackend.user.common.model.PublicProfile;
import pl.galajus.brokenpediabackend.user.common.model.dto.PublicProfileDto;

import java.util.List;

public class AdminPostsWithCategoryMapper {

    public static List<ListAdminPostDto> mapPostToMainPagePostsWithCategories(List<AdminPost> posts) {
        return posts.stream().map(post ->
                ListAdminPostDto.builder()
                        .id(post.getId())
                        .title(post.getTitle())
                        .image(post.getImage())
                        .description(post.getDescription())
                        .slug(post.getSlug())
                        .author(mapPublicProfileToPublicProfileDto(post.getAuthor()))
                        .creationDate(post.getCreationDate())
                        .lastUpdateDate(post.getLastUpdateDate())
                        .categories(mapPostCategoriesToPostCategoryDto(post.getCategories()))
                        .isPublic(post.getIsPublic())
                        .build()).toList();
    }

    private static List<AdminCategoryDto> mapPostCategoriesToPostCategoryDto(List<AdminCategory> categories) {
        return categories.stream().map(cat ->
                AdminCategoryDto.builder()
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

}
