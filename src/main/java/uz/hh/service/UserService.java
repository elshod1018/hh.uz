package uz.hh.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.hh.domain.User;
import uz.hh.dto.UserCreateDTO;
import uz.hh.enums.Role;
import uz.hh.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User save(UserCreateDTO dto) {
        User authUser;
//        if (dto.getRole().equals(Role.EMPLOYER)) {
            authUser = User.builder()
                    .firstName(dto.getFirstName())
                    .lastName(dto.getLastName())
                    .fullName(dto.getFullName())
                    .phoneNumber(dto.getPhoneNumber())
                    .companyName(dto.getCompanyName())
                    .region(dto.getRegion())
                    .username(dto.getUsername())
                    .password(passwordEncoder.encode(dto.getPassword()))
                    .email(dto.getEmail())
                    .role(dto.getRole())
                    .build();
//        }
//        else {
//            authUser = User.builder()
//                    .fullName(dto.getFullName())
//                    .username(dto.getUsername())
//                    .password(passwordEncoder.encode(dto.getPassword()))
//                    .email(dto.getEmail())
//                    .role(Role.USER)
//                    .build();
//        }
        userRepository.save(authUser);
        return authUser;
    }

    public User findById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public User update(User user) {
        return userRepository.save(user);
    }
}
