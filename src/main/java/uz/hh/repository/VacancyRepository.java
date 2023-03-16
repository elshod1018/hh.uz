package uz.hh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.hh.domain.User;
import uz.hh.domain.Vacancy;

import java.util.Optional;

public interface VacancyRepository extends JpaRepository<Vacancy, String> {
    Optional<Vacancy> findByIdEqualsIgnoreCase(String s);
}
