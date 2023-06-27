package pl.galajus.brokenpediabackend.admin.post.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.galajus.brokenpediabackend.admin.category.model.dto.AdminCategoryDto;
import pl.galajus.brokenpediabackend.user.common.model.dto.PublicProfileDto;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class ListAdminPostDto {
    private Long id;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdateDate;
    private List<AdminCategoryDto> categories;
    private PublicProfileDto author;
    private String slug;
    private String title;
    private String description;
    private String image;
    private Boolean isPublic;
}
