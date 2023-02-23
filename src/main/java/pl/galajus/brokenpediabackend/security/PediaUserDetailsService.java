package pl.galajus.brokenpediabackend.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.galajus.brokenpediabackend.security.model.PediaUserDetails;
import pl.galajus.brokenpediabackend.security.model.User;
import pl.galajus.brokenpediabackend.security.service.UserService;

@Service
@RequiredArgsConstructor
public class PediaUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByUserName(username);
        PediaUserDetails pediaUserDetails = createPediaUserDetails(user);
        return pediaUserDetails;
    }

    @Transactional
    public UserDetails loadUserByUuid(String uuid) throws UsernameNotFoundException {
        User user = userService.getByUuid(uuid);
        PediaUserDetails pediaUserDetails = createPediaUserDetails(user);
        return pediaUserDetails;
    }

    private PediaUserDetails createPediaUserDetails(User user) {
        PediaUserDetails pediaUserDetails = new PediaUserDetails(
                user.getUsername(),
                user.getPassword(),
                user.getAuthorities().stream()
                        .map(userRole -> (GrantedAuthority) userRole::name)
                        .toList()
        );
        pediaUserDetails.setUuid(user.getUuid());
        return pediaUserDetails;
    }
}
