package uz.hh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.hh.domain.Chat;

import java.util.List;
import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat, String> {

    Chat getChatById(String id);

    Optional<Chat> findChatById(String id);

    List<Chat> findAllByUsersId(String id);
}
