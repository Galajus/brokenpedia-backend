package pl.galajus.brokenpediabackend.user.security.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.UUID;

@Getter
@Setter
public class PediaUserDetails extends User {

    private UUID uuid;

    public PediaUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public PediaUserDetails(String username, String password, boolean enabled, Collection<? extends GrantedAuthority> authorities) {
        this(username, password, enabled, true, true, true, authorities);

    }

    public PediaUserDetails(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
}
