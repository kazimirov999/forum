package com.it.forum.security;

import com.it.forum.domain.entities.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public class CustomUserDetails extends org.springframework.security.core.userdetails.User{

    private final User user;

    public CustomUserDetails(final User user, final Collection<? extends GrantedAuthority> authorities) {
        super(user.getLogin(), user.getPassword(), authorities);
        this.user = user;
    }

    public CustomUserDetails(final User user, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, final Collection<? extends GrantedAuthority> authorities) {
        super(user.getLogin(), user.getPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}