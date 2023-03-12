package uz.hh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.hh.domain.Chat;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, String> {
    List<Chat> findAllByChatId(String id);
}
