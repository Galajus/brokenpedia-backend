package pl.galajus.brokenpediabackend.user.post.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.galajus.brokenpediabackend.user.category.model.dto.CategoryDto;
import pl.galajus.brokenpediabackend.user.profile.model.dto.PublicProfileDto;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class MainPagePostDto {
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdateDate;
    private List<CategoryDto> categories;
    private PublicProfileDto author;
    private String slug;
    private String title;
    private String description;
    private String image;
}
