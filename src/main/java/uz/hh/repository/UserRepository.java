package uz.hh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.hh.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    @Query("select u from users u where upper(u.username) = upper(?1)")
    Optional<User> findByUsername(String username);

}
