package uz.hh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.hh.domain.Vacancy;

import java.util.List;


public interface VacancyRepository extends JpaRepository<Vacancy, String> {
    Vacancy getVacancyById(String id);
}
