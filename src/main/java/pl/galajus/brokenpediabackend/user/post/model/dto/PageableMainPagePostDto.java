package pl.galajus.brokenpediabackend.user.post.model.dto;

import org.springframework.data.domain.Page;

public record PageableMainPagePostDto (Page<MainPagePostDto> mainPagePosts) {
}
