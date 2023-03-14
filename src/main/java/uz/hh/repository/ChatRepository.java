package uz.hh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.hh.domain.*;

import java.util.List;
import java.util.Set;

public interface ChatRepository extends JpaRepository<Chat, String> {
    List<Chat> findAllByCandidateId(String id);

    User findByCandidateId(String id);

    Vacancy findByVacancyId(String id);

    Employer findByEmployerId(String employerId);

    Set<Message> findAllMessagesByMessageId(String messageId);

    Set<Chat> findAllByCandidateIdAndIsRead(String userId, Boolean isRead);
}
