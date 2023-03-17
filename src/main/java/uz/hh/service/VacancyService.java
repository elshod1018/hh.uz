package uz.hh.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.hh.config.security.UserSession;
import uz.hh.domain.User;
import uz.hh.domain.Vacancy;
import uz.hh.dto.VacancyCreateDto;
import uz.hh.enums.Role;
import uz.hh.repository.VacancyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VacancyService {
    private final VacancyRepository vacancyRepository;
    private final UserSession userSession;

    public Vacancy getById(String vacancyId) {
        return vacancyRepository.getVacancyById(vacancyId);
    }

    public Vacancy create(VacancyCreateDto dto) {
        Vacancy vacancy = Vacancy.builder()
                .title(dto.getTitle())
                .application_deadline(dto.getApplication_deadline())
                .emp_form(dto.getEmp_form())
                .emp_type(dto.getEmp_type())
                .educationLevel(dto.getEducationLevel())
                .experienceYear(dto.getExperienceYear())
                .description(dto.getDescription())
                .region(dto.getRegion())
                .language(dto.getLanguage())
                .language_level(dto.getLanguage_level())
                .currency(dto.getCurrency())
                .salary(dto.getSalary())
                .market(dto.getMarket())
                .employer(userSession.getUser())
                .build();
        return vacancyRepository.save(vacancy);
    }

    public List<Vacancy> findAll() {
        vacancyRepository.findAll();
        return null;
    }
}
