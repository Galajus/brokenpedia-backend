package pl.galajus.brokenpediabackend.user.profile.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

@Getter
@ToString
public class ProfileNicknameDto {

    @NotBlank
    @Length(min = 5,  max = 18)
    @Pattern(regexp = "^[a-zA-Z]\\w*$")
    private String nickname;
}
