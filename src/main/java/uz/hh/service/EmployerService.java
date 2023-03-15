package uz.hh.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.hh.domain.Employer;
import uz.hh.repository.EmployerRepository;

@Service
@RequiredArgsConstructor
public class EmployerService {
    private final EmployerRepository employerRepository;

    public Employer getById(String employerId) {
        return employerRepository.getEmployerById(employerId);
    }
}
