package uz.hh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import uz.hh.domain.Chat;

import java.util.List;
import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat, String> {
    @Query("select c from Chat c inner join c.users users where users.id = ?1")
    List<Chat> findByUsers_Id(String id);

    @Query("select c from Chat c where c.vacancy.id = ?1")
    List<Chat> findByVacancy_Id(String id);




}
