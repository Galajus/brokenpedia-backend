package pl.galajus.brokenpediabackend.admin.post.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.galajus.brokenpediabackend.admin.post.model.AdminPost;
import pl.galajus.brokenpediabackend.admin.post.model.dto.PageablePageAdminPostDto;
import pl.galajus.brokenpediabackend.admin.post.service.AdminPostService;

import java.security.Principal;

@RestController
@RequestMapping("admin/posts")
@RequiredArgsConstructor
public class AdminPostController {

    private final AdminPostService adminPostService;

    @GetMapping
    public PageablePageAdminPostDto getPostToMainList() {
        return adminPostService.getPostFromNewest(0);
    }

    @GetMapping("/{id}")
    public AdminPost getSinglePost(@PathVariable Long id) {
        return adminPostService.getSinglePost(id);
    }

    @PostMapping
    public AdminPost createPost(@RequestBody AdminPost adminPost, Principal principal) {
        return adminPostService.createPost(adminPost, principal);
    }

    @PutMapping
    public AdminPost updatePost(@RequestBody AdminPost adminPost) {
        return adminPostService.updatePost(adminPost);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        adminPostService.deletePost(id);
    }

}
