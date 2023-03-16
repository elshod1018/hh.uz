package uz.hh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.hh.config.security.UserSession;
import uz.hh.domain.User;
import uz.hh.domain.Vacancy;
import uz.hh.dto.VacancyCreateDto;
import uz.hh.repository.VacancyRepository;

import java.time.LocalDate;

@Controller
@RequestMapping("/vacancy")
public class VacancyController {
    private final UserSession userSession;
    private final VacancyRepository vacancyRepository;

    public VacancyController(UserSession userSession, VacancyRepository vacancyRepository) {
        this.userSession = userSession;
        this.vacancyRepository = vacancyRepository;
    }

    @GetMapping("/main")
    public String main() {
        return "vacancy/main";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("localDate", LocalDate.now());
        return "vacancy/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute VacancyCreateDto dto){
        User user = userSession.getUser();
        System.out.println("keldii");
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
                .employer(user)
                .build();
        Vacancy savedVacancy = vacancyRepository.save(vacancy);
        System.out.println(savedVacancy);
//        model.addAttribute("vacancyId",savedVacancy.getId());
        return "redirect:/vacancy/getVacancy?vacancyId="+savedVacancy.getId();

    }

    @GetMapping("/getVacancy")
    public String getVacancy(@RequestParam(name = "vacancyId")String vacancyId, Model model){
        vacancyRepository.findById(vacancyId);
        model.addAttribute("vacancyId", vacancyId);
        return "vacancy/get";
    }



}
