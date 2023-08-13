package pl.galajus.brokenpediabackend.user.post.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.galajus.brokenpediabackend.user.category.model.dto.CategoryDto;
import pl.galajus.brokenpediabackend.user.common.model.PublicProfile;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class SinglePostDto {

    private Long id;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdateDate;
    private List<CategoryDto> categories;
    private PublicProfile author;
    private String slug;
    private String title;
    private String description;
    private String content;
    private String image;
    private Long views;
    private String nextPostSlug;
    private String previousPostSlug;

}
