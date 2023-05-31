package pl.galajus.brokenpediabackend.user.security.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class RegisterCredentials {
    @Email
    private String username;
    @NotBlank
    @Length(min = 5,  max = 32)
    private String password;
    @NotBlank
    private String repeatPassword;
    @NotBlank
    @Length(min = 5,  max = 18)
    @Pattern(regexp = "^[a-zA-Z]\\w*$")
    private String nickname;
}
