package uz.hh.service;

import javassist.NotFoundException;
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
    private final UserService userService;

    public Vacancy getById(String vacancyId) {
        return vacancyRepository.findById(vacancyId).orElse(null);
    }

    public List<Vacancy> getAllVacancy() {
        User user = userSession.getUser();
        if (user == null) {
            return vacancyRepository.findAll();
        } else if (Role.USER.equals(user.getRole())) {
            return vacancyRepository.findAll();
        }
        return vacancyRepository.findByEmployerId(user.getId());
    }


    public Vacancy create(VacancyCreateDto dto) {
        User user = userSession.getUser();
        System.out.println(user.getId());
        Vacancy vacancy = Vacancy.builder()
                .title(dto.getTitle())
                .companyName(user.getCompanyName())
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
                .employer(user)
                .build();
        return vacancyRepository.save(vacancy);
    }

    public String delete(String vacancyId) {
        vacancyRepository.updateIs_deletedById(vacancyId);
        return vacancyId;
    }

    public Vacancy update(VacancyCreateDto dto, String vacancyId) {

        try {
            User user = userSession.getUser();
            Vacancy vacancy= vacancyRepository.findById(vacancyId)
                    .orElseThrow(() -> new NotFoundException("Vacancy not found with id " + vacancyId));
            vacancy.setTitle(dto.getTitle());
            vacancy.setCompanyName(user.getCompanyName());
            vacancy.setApplication_deadline(dto.getApplication_deadline());
            vacancy.setEmp_form(dto.getEmp_form());
            vacancy.setEmp_type(dto.getEmp_type());
            vacancy.setEducationLevel(dto.getEducationLevel());
            vacancy.setExperienceYear(dto.getExperienceYear());
            vacancy.setDescription(dto.getDescription());
            vacancy.setRegion(dto.getRegion());
            vacancy.setLanguage_level(dto.getLanguage_level());
            vacancy.setLanguage(dto.getLanguage());
            vacancy.setCurrency(dto.getCurrency());
            vacancy.setSalary(dto.getSalary());
            vacancy.setMarket(dto.getMarket());
            vacancy.setEmployer(user);

            return vacancyRepository.save(vacancy);

        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

//    public List<Vacancy> findAll() {
//        return vacancyRepository.findAll();
//    }
}
