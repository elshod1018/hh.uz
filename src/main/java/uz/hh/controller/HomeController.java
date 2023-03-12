package uz.hh.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.hh.config.security.UserDetails;

@Controller
@RequestMapping
public class HomeController {
    @GetMapping({"/home", "/main", "/", ""})
    public String homePage(@ModelAttribute Model model, @AuthenticationPrincipal UserDetails user) {

        return "home";
    }
}
