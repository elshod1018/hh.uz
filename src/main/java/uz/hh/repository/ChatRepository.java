package uz.hh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.hh.domain.Chat;

import java.util.List;
import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat, String> {
    List<Chat> findAllByCandidateId(String id);

    List<Chat> findAllByEmployerId(String id);

    Chat getChatById(String id);
}
