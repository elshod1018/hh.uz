package uz.hh.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.hh.config.security.UserSession;

import java.util.Random;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserSession userSession;


    @GetMapping({"/","/home"})
    public String homePage() {
        return "home";
    }

/*    @ExceptionHandler(CustomRuntimeException.class)
    public String exception(Model model, CustomRuntimeException e) {
        model.addAttribute("error", e.getMessage());
        return "error";
    }*/
}
