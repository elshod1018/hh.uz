package uz.hh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import uz.hh.config.enums.EmploymentForm;
import uz.hh.domain.Vacancy;

import java.time.LocalDate;

@Controller
@RequestMapping("/vacancy")
public class VacancyController {

    @GetMapping("/main")
    public String main() {
        return "vacancy/main";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("localDate", LocalDate.now());
        model.addAttribute("selected_emp_form", EmploymentForm.HYBRID);
        model.addAttribute("selected_emp_form", EmploymentForm.REMOTE);
        model.addAttribute("selected_emp_form", EmploymentForm.IN_OFFICE);
        return "vacancy/create";
    }


}
