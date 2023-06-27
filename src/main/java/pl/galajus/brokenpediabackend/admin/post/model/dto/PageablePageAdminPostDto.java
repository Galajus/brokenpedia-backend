package pl.galajus.brokenpediabackend.admin.post.model.dto;

import org.springframework.data.domain.Page;

public record PageablePageAdminPostDto (Page<ListAdminPostDto> mainPagePosts){
}
