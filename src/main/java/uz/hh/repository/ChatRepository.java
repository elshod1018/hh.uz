package uz.hh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.hh.domain.Chat;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, String> {
    @Query("select c from Chat c inner join c.users users where users.id = ?1")
    List<Chat> findAllByUserId(String id);

    @Query("select c from Chat c where c.vacancy.id = ?1")
    List<Chat> findAllByVacancyId(String id);




}
