package uz.hh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.hh.domain.Message;
import uz.hh.domain.Vacancy;

public interface VacancyRepository extends JpaRepository<Vacancy, String> {
    Vacancy getVacancyById(String id);
}
