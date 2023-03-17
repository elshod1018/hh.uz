package uz.hh.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import uz.hh.service.VacancyService;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final VacancyService vacancyService;

    @GetMapping({"/", "/home"})
    public String homePage(Model model) {
        model.addAttribute("vacancies", vacancyService.findAll());
        return "home";
    }
    @PostMapping({"/", "/home"})
    public String home(Model model) {
        model.addAttribute("vacancies", vacancyService.findAll());
        return "home";
    }

/*    @ExceptionHandler(CustomRuntimeException.class)
    public String exception(Model model, CustomRuntimeException e) {
        model.addAttribute("error", e.getMessage());
        return "error";
    }*/
}
