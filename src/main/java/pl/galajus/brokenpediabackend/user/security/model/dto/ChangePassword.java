package pl.galajus.brokenpediabackend.user.security.model.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ChangePassword {
    @Size(min = 8, message = "Min length = 8")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-zżźćńółęąś])(?=.*[A-ZŻŹĆĄŚĘŁÓŃ]).{8,}$", message = "Not secure password")
    private String password;
    private String repeatPassword;
    private String hash;
}
