package uz.hh.handler;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("uz.hh")
public class CustomExceptionHandler {
    @ExceptionHandler(Exception.class)
    public String exception(Model model, Exception exception) {
        model.addAttribute("errorM", exception.getMessage());
        return "error";
    }
}
