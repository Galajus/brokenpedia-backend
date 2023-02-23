package pl.galajus.brokenpediabackend.security.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.galajus.brokenpediabackend.security.model.User;
import pl.galajus.brokenpediabackend.security.model.dto.ChangePassword;
import pl.galajus.brokenpediabackend.security.model.dto.EmailObject;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PasswordService {

    private final UserService userService;

    @Transactional
    public void sendLostPasswordLink(EmailObject email) {
        User user = userService.getByUserName(email.getEmail());
        String hash = generateHash(user);

        user.setLostPasswordHash(hash);
        user.setLostPasswordHashDate(LocalDateTime.now());

        //TODO: Send reset password email
        /*emailClientService.getInstance()
                .send(email.getEmail(), "Zresetuj hasło",
                        createMessage(createLink(hash)));*/
    }

    @Transactional
    public void changePassword(ChangePassword changePassword) {
        if(!Objects.equals(changePassword.getPassword(),
                changePassword.getRepeatPassword())) {
            throw new RuntimeException("Hasła nie są takie same");
        }
        User user = userService.getByLostPasswordHash(changePassword.getHash());
        if(user.getLostPasswordHashDate().plusMinutes(10).isAfter(LocalDateTime.now())){
            user.setPassword("{bcrypt}" + new BCryptPasswordEncoder().encode(changePassword.getPassword()));
            user.setLostPasswordHash(null);
            user.setLostPasswordHashDate(null);
        } else {
            throw new RuntimeException("Link stracił ważność");
        }
    }

    public static String generateHash(User user) {
        String toHash = user.getUuid().toString() + user.getUsername() + user.getPassword() + LocalDateTime.now();
        return DigestUtils.sha256Hex(toHash);
    }
}
