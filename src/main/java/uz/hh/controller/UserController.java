package uz.hh.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import uz.hh.domain.User;
import uz.hh.dto.UserCreateDTO;
import uz.hh.enums.Role;
import uz.hh.repository.UserRepository;
import uz.hh.service.UserService;

import java.util.Objects;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/register")
    public ModelAndView registerPage() {
        var mav = new ModelAndView();
        mav.setViewName("auth/register");
        return mav;
    }

    @GetMapping("/registerUser")
    public String registerUserPage(Model model) {
        UserCreateDTO userCreateDTO = new UserCreateDTO();
        userCreateDTO.setRole(Role.USER);
        model.addAttribute("user", userCreateDTO);
        return "auth/registerUser";
    }

    @GetMapping("/registerEmployer")
    public String registerEmployerPage(Model model) {
        UserCreateDTO employerCreateDTO = new UserCreateDTO();
        employerCreateDTO.setRole(Role.EMPLOYER);
        model.addAttribute("user", employerCreateDTO);
        return "auth/registerUser";
    }

    @PostMapping("/registerUser")
    public String registerUser(@Valid @ModelAttribute("user") UserCreateDTO dto, BindingResult errors) {
        if (errors.hasErrors()) {
            int errorCount = errors.getFieldErrorCount();
            if ((dto.getRole().equals(Role.USER) && errorCount > 5) || (dto.getRole().equals(Role.EMPLOYER) && errorCount > 1)) {
                return "auth/registerUser";
            }
        }
        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            errors.rejectValue("password", "", "auth.user.passwords.not.match");
            errors.rejectValue("confirmPassword", "", "auth.user.passwords.not.match");
            return "auth/registerUser";
        }
        userService.save(dto);
        return "redirect:/auth/login";
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
