package uz.hh.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import uz.hh.config.security.UserSession;
import uz.hh.domain.User;
import uz.hh.domain.Vacancy;
import uz.hh.service.VacancyService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final VacancyService vacancyService;
    private final UserSession userSession;

    @GetMapping({"/", "/home"})
    public String homePage(Model model) {
        User user = userSession.getUser();
        List<Vacancy> vacancies = vacancyService.getAllVacancy();
        model.addAttribute("vacancies", vacancies);
        model.addAttribute("user", user);
        return "home";
    }

    @PostMapping({"/", "/home"})
    public String home(Model model, @ModelAttribute String search) {
        System.out.println(search);
        model.addAttribute("vacancies", vacancyService.getAllVacancy());
        return "home";
    }

/*    @ExceptionHandler(CustomRuntimeException.class)
    public String exception(Model model, CustomRuntimeException e) {
        model.addAttribute("error", e.getMessage());
        return "error";
    }*/
}
