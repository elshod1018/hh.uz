package uz.hh.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.hh.config.security.UserSession;
import uz.hh.domain.Chat;
import uz.hh.dto.ChatCreateDTO;
import uz.hh.service.ChatService;

import java.util.List;
import java.util.Objects;

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
        if (Objects.isNull(chat)) {
            return "redirect:/chat/userchats";
        }
        model.addAttribute(chat);
        return "chat/message";
    }

    @PostMapping("/message")
    public String message() {

        return "redirect:/chat/message";
    }

}
