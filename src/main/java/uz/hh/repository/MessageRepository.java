package uz.hh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.hh.domain.Message;

import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message, String> {
}
