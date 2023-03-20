package uz.hh.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.hh.config.security.UserSession;
import uz.hh.domain.Chat;
import uz.hh.domain.User;
import uz.hh.domain.Vacancy;
import uz.hh.dto.VacancyCreateDto;
import uz.hh.enums.VacancyStatus;
import uz.hh.service.ChatService;
import uz.hh.service.VacancyService;

import java.time.LocalDate;
import java.util.Set;

@Controller
@RequiredArgsConstructor
@RequestMapping("/vacancy")
public class VacancyController {
    private final VacancyService vacancyService;
    private final ChatService chatService;
    private final UserSession userSession;

    @GetMapping("/main")
    @PreAuthorize("hasAnyRole('EMPLOYER')")
    public String main() {
        return "vacancy/main";
    }

    @GetMapping("/create")
    @PreAuthorize("hasAnyRole('EMPLOYER')")
    public String create(Model model) {
        model.addAttribute("localDate", LocalDate.now());
        return "vacancy/create";
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('EMPLOYER')")
    public String create(@ModelAttribute VacancyCreateDto dto) {
        Vacancy savedVacancy = vacancyService.create(dto);
        return "redirect:/vacancy/getVacancy?vacancyId=" + savedVacancy.getId();

    }

    @GetMapping("/getVacancy")
    public String getVacancy(@RequestParam(name = "vacancyId") String vacancyId, Model model) {
        Vacancy vacancy = vacancyService.getById(vacancyId);
        model.addAttribute("vacancy", vacancy);
        Chat chat = chatService.getChatByVacancyId(vacancyId);
        if (chat != null) {
            Set<User> users = chat.getUsers();
            if (users != null && users.contains(userSession.getUser())) {
                VacancyStatus status = chat.getStatus();
                model.addAttribute("status", status);
            }
        }
        return "vacancy/get";
    }


}
