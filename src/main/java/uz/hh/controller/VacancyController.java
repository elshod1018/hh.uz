package uz.hh.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.hh.config.security.UserSession;
import uz.hh.domain.User;
import uz.hh.domain.Vacancy;
import uz.hh.dto.VacancyCreateDto;
import uz.hh.enums.Role;
import uz.hh.repository.UserRepository;
import uz.hh.service.UserService;
import uz.hh.service.VacancyService;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/vacancy")
@PreAuthorize("hasRole('EMPLOYER')")
public class VacancyController {
    private final VacancyService vacancyService;
    private final UserSession userSession;
    private final UserService userService;

    @GetMapping("/main")
    @PreAuthorize("hasRole('EMPLOYER')")
    public String main() {
        return "vacancy/main";
    }

    @GetMapping("/create")
    @PreAuthorize("hasRole('EMPLOYER')")
    public String create(Model model) {
        model.addAttribute("localDate", LocalDate.now());
        return "vacancy/create";
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('EMPLOYER')")
    public String create(@ModelAttribute VacancyCreateDto dto) {
        Vacancy savedVacancy = vacancyService.create(dto);
        return "redirect:/vacancy/getVacancy?vacancyId=" + savedVacancy.getId();

    }

    @GetMapping("/edit")
    @PreAuthorize("hasRole('EMPLOYER')")
    public String edit(@RequestParam(name = "vacancyId") String vacancyId, Model model) {
        Vacancy vacancy = vacancyService.getById(vacancyId);
        model.addAttribute("vacancy", vacancy);
        model.addAttribute("localdate", vacancy.getApplication_deadline());
        return "vacancy/edit";
    }

    @PostMapping("/edit")
    @PreAuthorize("hasRole('EMPLOYER')")
    public String edit(@ModelAttribute VacancyCreateDto dto, @RequestParam(name = "vacancyId") String vacancyId) {
//        String  vacancyId = (String) model.getAttribute("vacancyId");
        Vacancy updatedVacancy = vacancyService.update(dto, vacancyId);
        System.out.println(updatedVacancy.getId());
        return "redirect:/home";
    }

//    @GetMapping("/delete")
//    @PreAuthorize("hasRole('EMPLOYER')")
//    public String deletePage(Model model, @ModelAttribute(name = "vacancyId") String vacancyId) {
//        model.addAttribute("vacancy", vacancyService.getById(vacancyId));
//        return "";
//    }

    @PostMapping("/delete")
    @PreAuthorize("hasRole('EMPLOYER')")
    public String delete(@ModelAttribute(name = "vacancyId") String vacancyId) {
        vacancyService.delete(vacancyId);
        return "redirect:/home";
    }

    @GetMapping("/getVacancy")
    @PreAuthorize("permitAll()")
    public String getVacancy(@RequestParam(name = "vacancyId") String vacancyId, Model model) {
        User user = userSession.getUser();
        Vacancy vacancy = vacancyService.getById(vacancyId);
        model.addAttribute("vacancy", vacancy);
        model.addAttribute("user", user);
        return "vacancy/get";
    }
}
