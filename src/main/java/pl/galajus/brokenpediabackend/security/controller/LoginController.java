package pl.galajus.brokenpediabackend.security.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.galajus.brokenpediabackend.security.model.LoginCredentials;
import pl.galajus.brokenpediabackend.security.model.PediaUserDetails;
import pl.galajus.brokenpediabackend.security.model.RegisterCredentials;
import pl.galajus.brokenpediabackend.security.model.Token;
import pl.galajus.brokenpediabackend.security.model.User;
import pl.galajus.brokenpediabackend.security.model.UserRole;
import pl.galajus.brokenpediabackend.security.model.dto.ConfirmAccount;
import pl.galajus.brokenpediabackend.security.service.PasswordService;
import pl.galajus.brokenpediabackend.security.service.UserService;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    @Value("${jwt.expirationTime}")
    private Long expirationTime;
    @Value("${jwt.secret}")
    private String secret;

    @PostMapping("/login")
    public Token login(@RequestBody LoginCredentials loginCredentials) {
        return authenticate(loginCredentials.getUsername(), loginCredentials.getPassword());
    }

    @PostMapping("/register")
    public Token register(@RequestBody @Valid RegisterCredentials registerCredentials) {
        if (!registerCredentials.getPassword().equals(registerCredentials.getRepeatPassword())) {
            throw new IllegalArgumentException("Passwords not identical");
        }
        if (userService.userExist(registerCredentials.getUsername())) {
            throw new IllegalArgumentException("User exist");
        }
        userService.createUser(buildUser(registerCredentials));

        return authenticate(registerCredentials.getUsername(), registerCredentials.getPassword());
    }

    @PostMapping("/confirmAccount")
    public Boolean confirmAccount(@RequestBody ConfirmAccount confirmAccount) {
        User user = userService.getByConfirmAccountHash(confirmAccount.getHash());
        if (user.isEnabled()) {
            return false;
        }
        user.setEnabled(true);
        userService.updateUser(user);
        return true;
    }

    private User buildUser(RegisterCredentials registerCredentials) {
        User user = User.builder()
                .username(registerCredentials.getUsername())
                .password("{bcrypt}" + new BCryptPasswordEncoder().encode(registerCredentials.getPassword()))
                .enabled(true)
                .authorities(List.of(UserRole.ROLE_CUSTOMER))
                .build();
        user.setConfirmAccountHash(PasswordService.generateHash(user));
        return user;
    }

    private Token authenticate(String username, String password) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        PediaUserDetails principal = (PediaUserDetails) authenticate.getPrincipal();
        String token = JWT.create()
                .withSubject(principal.getUuid().toString())
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                .sign(Algorithm.HMAC256(secret));

        return new Token(
                token,
                hasPermission(principal, UserRole.ROLE_ADMIN),
                hasPermission(principal, UserRole.ROLE_MODERATOR)
        );
    }

    private boolean hasPermission(PediaUserDetails principal, UserRole role) {
        return principal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .filter(s -> role.name().equals(s))
                .map(s -> true)
                .findFirst()
                .orElse(false);
    }
}
