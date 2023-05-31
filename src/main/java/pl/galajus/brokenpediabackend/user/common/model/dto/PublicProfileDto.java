package pl.galajus.brokenpediabackend.user.common.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class PublicProfileDto {
    private UUID uuid;
    private String nickname;
}
