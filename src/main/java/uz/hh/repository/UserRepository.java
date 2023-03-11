package uz.hh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.hh.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsernameIgnoreCase(String username);
}
