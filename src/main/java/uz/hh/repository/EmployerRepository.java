package uz.hh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.hh.domain.Employer;
import uz.hh.domain.Message;

@Repository
public interface EmployerRepository extends JpaRepository<Employer, String> {
    Employer getEmployerById(String id);
}
