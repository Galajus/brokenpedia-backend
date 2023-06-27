package pl.galajus.brokenpediabackend.user.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.galajus.brokenpediabackend.user.post.model.Post;
import pl.galajus.brokenpediabackend.user.post.model.dto.MainPagePostDto;
import pl.galajus.brokenpediabackend.user.post.model.dto.PageableMainPagePostDto;
import pl.galajus.brokenpediabackend.user.post.repostitory.PostRepository;
import pl.galajus.brokenpediabackend.user.post.service.mapper.PostsWithCategoryMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private static final Integer PAGE_SIZE = 60;

    @Transactional(readOnly = true)
    public PageableMainPagePostDto getPostFromNewest(Integer page) {

        PageRequest pageable = PageRequest.of(page, PAGE_SIZE, Sort.by(Sort.Direction.DESC, "creationDate"));
        Page<Long> pagedPostsIds = postRepository.findAllByIsPublicOnlyIds(true, pageable);
        List<Post> pagedPosts = postRepository.findPublicPostByIdInWithCategories(pagedPostsIds.getContent(), pageable.getSort());
        List<MainPagePostDto> mainPagePostsDtos = PostsWithCategoryMapper.mapPostToMainPagePostsWithCategories(pagedPosts);

        return new PageableMainPagePostDto(new PageImpl<>(mainPagePostsDtos, pageable, pagedPostsIds.getTotalElements()));
    }

    public Post getSinglePublicPostBySlug(String slug) {
        return postRepository.findPostBySlugAndIsPublic(slug, true).orElseThrow();
    }

    @Transactional(readOnly = true)
    public PageableMainPagePostDto getPostByCategory(Integer page, String category) {
        PageRequest pageable = PageRequest.of(page, PAGE_SIZE, Sort.by(Sort.Direction.DESC, "creationDate"));
        Page<Long> pagedPostsIds = postRepository.findByCategoryAndIsPublicOnlyIds(category, true, pageable);
        List<Post> pagedPosts = postRepository.findPublicPostByIdInWithCategories(pagedPostsIds.getContent(), pageable.getSort());
        List<MainPagePostDto> mainPagePostsDtos = PostsWithCategoryMapper.mapPostToMainPagePostsWithCategories(pagedPosts);

        return new PageableMainPagePostDto(new PageImpl<>(mainPagePostsDtos, pageable, pagedPostsIds.getTotalElements()));
    }
}
