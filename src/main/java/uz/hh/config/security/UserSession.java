package uz.hh.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.hh.domain.User;

@Component
public class UserSession {
    public User getUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        Object authUserDetails = authentication.getPrincipal();
        if (authUserDetails instanceof UserDetails userDetails)
            return userDetails.getUser();
        return null;
    }

    public String getId() {
        User user = getUser();
        if (user != null)
            return user.getId();
        return null;
    }
}