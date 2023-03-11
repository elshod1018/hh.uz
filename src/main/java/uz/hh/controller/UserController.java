package uz.hh.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import uz.hh.domain.User;
import uz.hh.dto.UserCreateDTO;
import uz.hh.repository.UserRepository;

@Controller
@RequestMapping("/auth")
public class UserController {
    private final UserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository authUserRepository, PasswordEncoder passwordEncoder) {
        this.authUserRepository = authUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public ModelAndView registerPage() {
        var mav = new ModelAndView();
        mav.setViewName("auth/register");
        return mav;
    }
    @PostMapping("/register")
    public String register(@ModelAttribute UserCreateDTO dto) {
        User authUser = User.builder()
                .username(dto.username())
                .password(passwordEncoder.encode(dto.password()))
                .email(dto.email())
                .build();
        authUserRepository.save(authUser);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public ModelAndView loginPage(@RequestParam(required = false) String error) {
        var mav = new ModelAndView();
        mav.addObject("error", error);
        mav.setViewName("auth/login");
        return mav;
    }

    @GetMapping("/logout")
    public ModelAndView logoutPage() {
        var mav = new ModelAndView();
        mav.setViewName("auth/logout");
        return mav;
    }
}
