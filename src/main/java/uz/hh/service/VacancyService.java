package uz.hh.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.hh.domain.Vacancy;
import uz.hh.repository.VacancyRepository;

@Service
@RequiredArgsConstructor
public class VacancyService {
    private final VacancyRepository vacancyRepository;

    public Vacancy getById(String vacancyId) {
        return vacancyRepository.getVacancyById(vacancyId);
    }
}
