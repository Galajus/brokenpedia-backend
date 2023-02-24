package pl.galajus.brokenpediabackend.security.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.galajus.brokenpediabackend.common.mail.EmailClientService;
import pl.galajus.brokenpediabackend.security.model.User;
import pl.galajus.brokenpediabackend.security.model.dto.ChangePassword;
import pl.galajus.brokenpediabackend.security.model.dto.EmailObject;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PasswordService {

    private final UserService userService;
    private final EmailClientService emailClientService;
    @Value("${app.serviceAddress}")
    private String serviceAddress;

    public void sendAccountConfirmationLink(User user) {
        emailClientService.getInstance()
                .send(user.getUsername(),
                        "Potwierdź konto w Brokenpedia.com",
                        createConfirmAccountMessage(createConfirmAccountLink(user.getConfirmAccountHash()))
                );
    }

    @Transactional
    public void sendLostPasswordLink(EmailObject email) {
        User user = userService.getByUserName(email.getEmail());
        String hash = generateHash(user);

        user.setLostPasswordHash(hash);
        user.setLostPasswordHashDate(LocalDateTime.now());

        emailClientService.getInstance().send(email.getEmail(),
                "Zresetuj hasło",
                createLostPasswordMessage(createLostPasswordLink(hash)));
    }

    @Transactional
    public void changePassword(ChangePassword changePassword) {
        if (!Objects.equals(changePassword.getPassword(),
                changePassword.getRepeatPassword())) {
            throw new RuntimeException("Hasła nie są takie same");
        }
        User user = userService.getByLostPasswordHash(changePassword.getHash());
        if (user.getLostPasswordHashDate().plusMinutes(10).isAfter(LocalDateTime.now())) {
            user.setPassword("{bcrypt}" + new BCryptPasswordEncoder().encode(changePassword.getPassword()));
            user.setLostPasswordHash(null);
            user.setLostPasswordHashDate(null);
        } else {
            throw new RuntimeException("Link stracił ważność");
        }
    }

    public static String generateHash(User user) {
        UUID uuid = user.getUuid() != null ? user.getUuid() : UUID.randomUUID();
        String toHash = uuid + user.getUsername() + user.getPassword() + LocalDateTime.now();
        return DigestUtils.sha256Hex(toHash);
    }

    private String createConfirmAccountMessage(String hashLink) {
        return "Dzięki za rejestracje na Brokenpedia.com :D" +
                "\n\nKliknij link, żeby aktywować konto: " +
                "\n" + hashLink +
                "\n\nPozdrawiam!";
    }

    private String createLostPasswordMessage(String hashLink) {
        return "Oto Twój link do zmiany hasła" +
                "\n\nKliknij link, żeby je zresetować: " +
                "\n" + hashLink +
                "\n\nPozdrawiam!";
    }

    private String createLostPasswordLink(String hash) {
        return serviceAddress + "/lost-password/" + hash;
    }

    private String createConfirmAccountLink(String hash) {
        return serviceAddress + "/confirm-account/" + hash;
    }
}
