package pl.galajus.brokenpediabackend.user.post.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.galajus.brokenpediabackend.user.post.controller.mapper.PostMapper;
import pl.galajus.brokenpediabackend.user.post.model.Post;
import pl.galajus.brokenpediabackend.user.post.model.dto.PageableMainPagePostDto;
import pl.galajus.brokenpediabackend.user.post.model.dto.SinglePostDto;
import pl.galajus.brokenpediabackend.user.post.service.PostService;
import pl.galajus.brokenpediabackend.user.post.service.PostViewerService;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostViewerService postViewerService;

    @GetMapping
    public PageableMainPagePostDto getNewestPosts() {
        return postService.getPostFromNewest(0);
    }

    @GetMapping("/category/{category}")
    public PageableMainPagePostDto getPostsByCategory(@PathVariable String category) {
        return postService.getPostByCategory(0, category);
    }

    @GetMapping("/{slug}")
    public SinglePostDto getPostBySlug(@PathVariable String slug, HttpServletRequest request) {
        Post post = postService.getSinglePublicPostBySlug(slug);
        postViewerService.incrementPostViews(post.getId(), request);
        return PostMapper.mapPostToSinglePostDto(post);
    }

}
