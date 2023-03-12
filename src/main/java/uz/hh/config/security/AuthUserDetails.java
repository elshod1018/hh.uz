package uz.hh.config.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.hh.domain.Permission;
import uz.hh.domain.Role;
import uz.hh.domain.Status;
import uz.hh.domain.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

public class AuthUserDetails implements UserDetails {

    private final User user;

    public AuthUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        var authRoles = Objects.requireNonNullElse(user.getRoles(), Collections.<Role>emptySet());
        var authorities = new ArrayList<SimpleGrantedAuthority>();
        authRoles.forEach(authRole -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + authRole.getCode()));
            Collection<Permission> authPermissions = Objects.requireNonNullElse(authRole.getPermissions(), Collections.emptySet());
            authPermissions.forEach(authPermission -> {
                authorities.add(new SimpleGrantedAuthority(authPermission.getCode()));
            });
        });
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }


    public User getUser() {
        return user;
    }

    public String getId() {
        return user.getId();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !user.getStatus().equals(Status.BLOCKED);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return Status.ACTIVE.equals(user.getStatus());
    }
}