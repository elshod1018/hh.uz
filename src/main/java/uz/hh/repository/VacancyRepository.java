package uz.hh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import uz.hh.domain.User;
import uz.hh.domain.Vacancy;

import java.util.List;

public interface VacancyRepository extends JpaRepository<Vacancy, String> {
    @Transactional
    @Modifying
    @Query("update Vacancy v set v.is_deleted = true where v.id = ?1")
    void updateIs_deletedById(String vacancyId);

    @Query("select v  from Vacancy v where v.employer =?1")
    List<Vacancy> findAllByEmployer(User employer);
}
