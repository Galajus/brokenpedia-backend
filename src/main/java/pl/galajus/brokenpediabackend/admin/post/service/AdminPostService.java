package pl.galajus.brokenpediabackend.admin.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.galajus.brokenpediabackend.admin.category.model.AdminCategory;
import pl.galajus.brokenpediabackend.admin.category.service.AdminCategoryService;
import pl.galajus.brokenpediabackend.admin.common.utils.SlugifyUtils;
import pl.galajus.brokenpediabackend.admin.post.model.AdminPost;
import pl.galajus.brokenpediabackend.admin.post.model.dto.ListAdminPostDto;
import pl.galajus.brokenpediabackend.admin.post.model.dto.PageablePageAdminPostDto;
import pl.galajus.brokenpediabackend.admin.post.repostitory.AdminPostRepository;
import pl.galajus.brokenpediabackend.admin.post.service.mapper.AdminPostsWithCategoryMapper;
import pl.galajus.brokenpediabackend.user.profile.model.PublicProfile;
import pl.galajus.brokenpediabackend.user.profile.model.Profile;
import pl.galajus.brokenpediabackend.user.profile.service.ProfileService;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminPostService {

    private final AdminPostRepository adminPostRepository;
    private final ProfileService profileService;
    private final AdminCategoryService adminCategoryService;
    private static final Integer PAGE_SIZE = 60;

    /*public Page<AdminPost> getPostFromNewest(Integer page) {
        return adminPostRepository.findAll(PageRequest.of(page, PAGE_SIZE, Sort.by(Sort.Direction.DESC, "creationDate")));
    }*/

    public AdminPost getSinglePost(Long id) {
        return adminPostRepository.findByIdWithCategoriesAndAuthor(id).orElseThrow();
    }

    @Transactional(readOnly = true)
    public PageablePageAdminPostDto getPostFromNewest(Integer page) {
        PageRequest pageable = PageRequest.of(page, PAGE_SIZE, Sort.by(Sort.Direction.DESC, "creationDate"));
        Page<Long> pagedPostsIds = adminPostRepository.findAllOnlyIds(true, pageable);
        List<AdminPost> pagedPosts = adminPostRepository.findPostByIdInWithCategories(pagedPostsIds.getContent(), pageable.getSort());
        List<ListAdminPostDto> mainPagePostsDtos = AdminPostsWithCategoryMapper.mapPostToMainPagePostsWithCategories(pagedPosts);

        return new PageablePageAdminPostDto(new PageImpl<>(mainPagePostsDtos, pageable, pagedPostsIds.getTotalElements()));
    }

    @Transactional
    public AdminPost createPost(AdminPost adminPost, Principal principal) {
        Profile profile = profileService.getProfile(principal.getName());
        PublicProfile publicProfile = PublicProfile.builder()
                .uuid(profile.getUuid())
                .nickname(profile.getNickname())
                .build();

        adminPost.setSlug(SlugifyUtils.slugifySlug(adminPost.getTitle()));
        adminPost.setCreationDate(LocalDateTime.now());
        adminPost.setAuthor(publicProfile);
        List<Long> categoriesIds = adminPost.getCategories().stream().map(AdminCategory::getId).toList();
        adminPost.setCategories(adminCategoryService.getCategoriesByIds(categoriesIds));
        return adminPostRepository.save(adminPost);

    }

    @Transactional
    public AdminPost updatePost(AdminPost newPost) {
        AdminPost oldPost = adminPostRepository.findById(newPost.getId()).orElseThrow();
        oldPost.setDescription(newPost.getDescription());
        oldPost.setIsPublic(newPost.getIsPublic());
        oldPost.setImage(newPost.getImage());
        oldPost.setContent(newPost.getContent());
        oldPost.setTitle(newPost.getTitle());
        oldPost.setLastUpdateDate(LocalDateTime.now());

        List<Long> categoriesIds = newPost.getCategories().stream().map(AdminCategory::getId).toList();
        oldPost.setCategories(adminCategoryService.getCategoriesByIds(categoriesIds));

        return adminPostRepository.save(oldPost);
    }

    public void deletePost(Long id) {
        adminPostRepository.deleteById(id);
    }

}
