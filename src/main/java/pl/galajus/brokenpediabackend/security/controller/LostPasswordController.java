package pl.galajus.brokenpediabackend.security.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.galajus.brokenpediabackend.security.model.dto.ChangePassword;
import pl.galajus.brokenpediabackend.security.model.dto.EmailObject;
import pl.galajus.brokenpediabackend.security.service.PasswordService;

@RestController
@RequiredArgsConstructor
public class LostPasswordController {

    private final PasswordService passwordService;

    @PostMapping("/lostPassword")
    public void lostPassword(@RequestBody EmailObject email) {
        passwordService.sendLostPasswordLink(email);
    }

    @PostMapping("/changePassword")
    public void changePassword(@RequestBody @Valid ChangePassword changePassword) {
        passwordService.changePassword(changePassword);
    }

}
