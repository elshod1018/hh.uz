package uz.hh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import uz.hh.repository.ChatRepository;

@Controller
@RequestMapping("/chat")
public class ChatController {
    private final ChatRepository chatRepository;

    public ChatController(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }


    @GetMapping("/allChats")
    public ModelAndView allChatsPage() {
        var mav = new ModelAndView();
        mav.setViewName("chat/allChats");
        return mav;
    }

    @GetMapping("/chat")
    public ModelAndView chatPage() {
        var mav = new ModelAndView();
        mav.setViewName("chat/chat");
        return mav;
    }

    @PostMapping("/newChat")
    public ModelAndView newChatPage() {
        var mav = new ModelAndView();
        mav.setViewName("chat/newChat");
        return mav;
    }

}
