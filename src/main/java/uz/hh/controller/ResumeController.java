package uz.hh.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
public class ResumeController {

    private final ResumeMapper resumeMapper;
    private final UserService userService;
    private final ResumeService resumeService;
    private final ResumeRepository repository;
    private final UserRepository userRepository;

    @GetMapping(value = "/{id}")
    public String create(Model model, @PathVariable String id) {
        Resume resume = resumeService.findResumesByUser_Id(id);
        model.addAttribute("resume", resume);
        if (resume != null) {
            return "/resume/Cv_2";
        }
        return "/resume/Cv";

    }

    @PostMapping(value = "/update/{id}")
    public String update(@ModelAttribute CreateResumeDto dto, @PathVariable String id) {
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

    @PostMapping(value = "/{id}")
    public String create(@ModelAttribute CreateResumeDto dto, @PathVariable String id) {
        Resume resume = resumeMapper.fromCreateDto(dto);
        User user = userService.findById(id);
        if (user != null) {
            resume.setUser(user);
        }
        repository.save(resume);
        return "/resume/simple";

    }


}
