package uz.hh.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.hh.config.security.UserSession;
import uz.hh.domain.Resume;
import uz.hh.domain.User;
import uz.hh.dto.CreateResumeDto;
import uz.hh.mapper.ResumeMapper;
import uz.hh.repository.ResumeRepository;
import uz.hh.repository.UserRepository;
import uz.hh.service.ResumeService;
import uz.hh.service.UserService;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/resume")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('USER')")
public class ResumeController {
    private final ResumeMapper resumeMapper;
    private final UserService userService;
    private final ResumeService resumeService;
    private final ResumeRepository repository;
    private final UserRepository userRepository;
    private final UserSession userSession;

    @GetMapping(value = "/create")
    public String create(Model model) {
        String userId = userSession.getId();
        Resume resume = resumeService.findResumesByUser_Id(userId);
        model.addAttribute("resume", resume);
        if (resume != null) {
            return "/resume/Cv_2";
        }
        return "/resume/Cv";

    }
    @PostMapping(value = "/create")
    public String create(@ModelAttribute CreateResumeDto dto) {
        Resume resume = resumeMapper.fromCreateDto(dto);
        User user = userSession.getUser();
        resume.setUser(user);
        repository.save(resume);
        return "redirect:/home";

    }

    @PostMapping(value = "/update")
    public String update(@ModelAttribute CreateResumeDto dto) {
        String id = userSession.getId();
        Resume resume1 = resumeService.findResumesByUser_Id(id);
        Resume resume = resumeService.findById(resume1.getId());
        if (resume != null) {
            resume.setUser(userService.findById(id));
            resume.setEmail(dto.getEmail());
            resume.setAddress(dto.getAddress());
            resume.setExperience(dto.getExperience());
            resume.setJob(dto.getJob());
            resume.setProfile(dto.getProfile());
            resume.setFirstName(dto.getFirstName());
            resume.setLastName(dto.getLastName());
            resume.setCreatedAt(LocalDateTime.now());
            resume.setPhoneNumber(dto.getPhoneNumber());
            resume.setTech_skill(dto.getTech_skill());
            repository.save(resume);
        }

        return "/resume/simple";

    }




}