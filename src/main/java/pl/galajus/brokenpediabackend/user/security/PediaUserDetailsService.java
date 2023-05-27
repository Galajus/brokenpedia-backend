package pl.galajus.brokenpediabackend.user.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.galajus.brokenpediabackend.user.security.model.PediaUserDetails;
import pl.galajus.brokenpediabackend.user.security.model.User;
import pl.galajus.brokenpediabackend.user.security.service.UserService;

@Service
@RequiredArgsConstructor
public class PediaUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByUserName(username);
        return createPediaUserDetails(user);
    }

    @Transactional
    public UserDetails loadUserByUuid(String uuid) throws UsernameNotFoundException {
        User user = userService.getByUuid(uuid);
        return createPediaUserDetails(user);
    }

    private PediaUserDetails createPediaUserDetails(User user) {
        PediaUserDetails pediaUserDetails = new PediaUserDetails(
                user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                user.getAuthorities().stream()
                        .map(userRole -> (GrantedAuthority) userRole::name)
                        .toList()
        );
        pediaUserDetails.setUuid(user.getUuid());
        return pediaUserDetails;
    }
}
