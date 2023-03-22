package uz.hh.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.hh.domain.Resume;

public interface ResumeRepository extends JpaRepository<Resume, String> {
    Resume findByUser_Id(String id);

}
