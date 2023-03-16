package uz.hh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.hh.domain.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, String> {
    Message getMessageById(String id);
}
