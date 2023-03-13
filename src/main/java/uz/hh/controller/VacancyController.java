package uz.hh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/vacancy")
public class VacancyController {

    @GetMapping("/main")
    public ModelAndView main(){
        var mv = new ModelAndView();
        mv.setViewName("vacancy/main");
        return mv;
    }
}
