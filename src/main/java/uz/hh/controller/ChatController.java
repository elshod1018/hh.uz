package uz.hh.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.hh.config.security.UserSession;
import uz.hh.domain.*;
import uz.hh.dto.ChatCreateDTO;
import uz.hh.service.ChatService;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Controller
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {
    private final UserSession userSession;
    private final ChatService chatService;

    @GetMapping("/userchats")
//    @PreAuthorize("hasAnyRole('USER')")
    public String userChatPage(Model model) {
        List<Chat> userChats = chatService.getUserChats(userSession.getId());
        model.addAttribute("chats", userChats);
        return "chat/userchats";
    }

    @GetMapping("/employerchats")
    @PreAuthorize("hasAnyRole('EMPLOYER')")
    public String employerChatPage(Model model) {
        List<Chat> employerChats = chatService.getEmployerChats(userSession.getId());
        model.addAttribute("chats", employerChats);
        return "userchats";
    }

    @GetMapping("/create")
    @PreAuthorize("hasAnyRole('USER')")
    public String createPage(@ModelAttribute ChatCreateDTO dto) {
        chatService.chatCreate(dto, userSession.getUser());
        return "chat/create";
    }

    @GetMapping("/message/{chatId:.+}")
    public String messagePage(Model model, @PathVariable String chatId) {
        Chat chat = chatService.getChatById(chatId);
        System.out.println(chat + " : " + chatId);
//        if (Objects.isNull(chat)) {
//            return "redirect:/chat/userchats";
//        }
        User user = new User("1", "Elshod", "elshod",
                "nuriddinovelshod@gmail.com", "1234",
                Status.ACTIVE, Set.of(), Set.of(), Set.of(), false, LocalDateTime.now());
        Vacancy vacancy = new Vacancy();
        Employer employer = new Employer();
        HashSet<Message> messages = new HashSet<>();
        chat = new Chat("1", user, vacancy, employer, messages, VacancyStatus.APPLIED, LocalDateTime.now(), LocalDateTime.now());
        model.addAttribute("chat", chat);
        return "chat/message";
    }

    @PostMapping("/message")
    public String message() {

        return "redirect:/chat/message";
    }

}
