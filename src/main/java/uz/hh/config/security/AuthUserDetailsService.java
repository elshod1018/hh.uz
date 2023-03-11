package uz.hh.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.hh.domain.User;
import uz.hh.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameIgnoreCase(username)
                .orElseThrow(() -> new UsernameNotFoundException("Bad Credentials"));
        return new UserDetails(user);
    }
}
