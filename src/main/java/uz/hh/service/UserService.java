package uz.hh.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.hh.domain.Chat;
import uz.hh.domain.User;
import uz.hh.dto.UserCreateDTO;
import uz.hh.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User save(UserCreateDTO dto) {
        User authUser = User.builder()
                .fullName(dto.getFullName())
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .email(dto.getEmail())
                .build();

        System.out.println("authUser = " + authUser);
        userRepository.save(authUser);
        return authUser;
    }
}
