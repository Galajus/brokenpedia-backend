package pl.galajus.brokenpediabackend.user.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.galajus.brokenpediabackend.user.post.controller.projection.ProjectionPostIdAndSlug;
import pl.galajus.brokenpediabackend.user.post.model.FrontPost;
import pl.galajus.brokenpediabackend.user.post.model.Post;
import pl.galajus.brokenpediabackend.user.post.model.dto.MainPagePostDto;
import pl.galajus.brokenpediabackend.user.post.model.dto.PageableMainPagePostDto;
import pl.galajus.brokenpediabackend.user.post.repostitory.PostRepository;
import pl.galajus.brokenpediabackend.user.post.service.mapper.PostsWithCategoryMapper;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private static final Integer PAGE_SIZE = 60;

    @Transactional(readOnly = true)
    public PageableMainPagePostDto getPostFromNewest(Integer page) {

        PageRequest pageable = PageRequest.of(page, PAGE_SIZE, Sort.by(Sort.Direction.DESC, "creationDate"));
        Page<Long> pagedPostsIds = postRepository.findAllByIsPublicOnlyIds(true, pageable);
        List<FrontPost> pagedPosts = postRepository.findPublicFrontPostByIdInWithCategories(pagedPostsIds.getContent(), pageable.getSort());
        List<MainPagePostDto> mainPagePostsDtos = PostsWithCategoryMapper.mapFrontPostToMainPagePostsWithCategories(pagedPosts);

        return new PageableMainPagePostDto(new PageImpl<>(mainPagePostsDtos, pageable, pagedPostsIds.getTotalElements()));
    }

    public Post getSinglePublicPostBySlug(String slug) {
        return postRepository.findPostBySlugAndIsPublic(slug, true).orElseThrow();
    }

    @Transactional(readOnly = true)
    public PageableMainPagePostDto getPostByCategory(Integer page, String category) {
        PageRequest pageable = PageRequest.of(page, PAGE_SIZE, Sort.by(Sort.Direction.DESC, "creationDate"));
        Page<Long> pagedPostsIds = postRepository.findByCategoryAndIsPublicOnlyIds(category, true, pageable);
        List<FrontPost> pagedPosts = postRepository.findPublicFrontPostByIdInWithCategories(pagedPostsIds.getContent(), pageable.getSort());
        List<MainPagePostDto> mainPagePostsDtos = PostsWithCategoryMapper.mapFrontPostToMainPagePostsWithCategories(pagedPosts);

        return new PageableMainPagePostDto(new PageImpl<>(mainPagePostsDtos, pageable, pagedPostsIds.getTotalElements()));
    }

    public String getNextPost(Long id) {
        Optional<ProjectionPostIdAndSlug> post = postRepository.findFirstByIsPublicAndIdAfterOrderByIdAsc(true, id);
        return post.map(ProjectionPostIdAndSlug::getSlug).orElse(null);
    }
    public String getPreviousPost(Long id) {
        Optional<ProjectionPostIdAndSlug> post = postRepository.findFirstByIsPublicAndIdBeforeOrderByIdDesc(true, id);
        return post.map(ProjectionPostIdAndSlug::getSlug).orElse(null);
    }
}
