package uz.hh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import uz.hh.config.security.UserSession;

import java.util.Random;

@Controller
public class HomeController {

    private final UserSession userSession;

    public HomeController(UserSession userSession) {
        this.userSession = userSession;
    }

//    @GetMapping("/home")
//    public String hasAdminRole(Model model) {
//        //var s = "{bcrypt}$sdjvbhksnfbpgdhjnvlworhgeirnkvmd";
//        //var ss = "{noop}passwor";
//        //System.out.println("userSession.getUser().getId() = " + userSession.getId());
//        return "home";
//    }

    @GetMapping({"/","/home"})
    public String homePage() {
//        if (new Random().nextBoolean()) {
//            throw new CustomRuntimeException("Just For Fun Exception");
//        }
        return "home";
    }

/*    @ExceptionHandler(CustomRuntimeException.class)
    public String exception(Model model, CustomRuntimeException e) {
        model.addAttribute("error", e.getMessage());
        return "error";
    }*/
}
