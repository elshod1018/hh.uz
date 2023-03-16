package uz.hh.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.hh.domain.Vacancy;
import uz.hh.dto.VacancyCreateDto;
import uz.hh.service.VacancyService;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
@RequestMapping("/vacancy")
public class VacancyController {
    private final VacancyService vacancyService;

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
    public String create(@ModelAttribute VacancyCreateDto dto) {
        Vacancy savedVacancy = vacancyService.create(dto);
        return "redirect:/vacancy/getVacancy?vacancyId=" + savedVacancy.getId();

    }

    @GetMapping("/getVacancy")
    public String getVacancy(@RequestParam(name = "vacancyId") String vacancyId, Model model) {
        Vacancy vacancy = vacancyService.getById(vacancyId);
        model.addAttribute("vacancy", vacancy);
        return "vacancy/get";
    }


}
