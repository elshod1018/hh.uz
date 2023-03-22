package uz.hh.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.hh.config.security.UserSession;
import uz.hh.domain.*;
import uz.hh.dto.ChatCreateDTO;
import uz.hh.dto.ChatUpdateDTO;
import uz.hh.enums.Role;
import uz.hh.enums.Status;
import uz.hh.enums.VacancyStatus;
import uz.hh.service.ChatService;
import uz.hh.service.VacancyService;

import java.time.LocalDateTime;
import java.util.*;

@Controller
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {
    private final UserSession userSession;
    private final ChatService chatService;
    private final VacancyService vacancyService;

    @GetMapping("/userchats")
    public String userChatPage(Model model) {
        model.addAttribute("sessionUser", userSession.getUser());
        List<Chat> userChats = chatService.getUserChats();
        model.addAttribute("chats", userChats);
        return "chat/userchats";
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('USER')")
    public String create(Model model, @ModelAttribute ChatCreateDTO dto) {
        String vacancyId = dto.getVacancyId();
        List<Chat> chatsByVacancyId = chatService.getChatByVacancyId(vacancyId);
        Chat chat1 = chatsByVacancyId.stream()
                .filter(chat -> {
                    Set<User> users = chat.getUsers();
                    User user1 = users.stream()
                            .filter(user -> user.getId().equals(userSession.getId()))
                            .findFirst()
                            .orElse(null);
                    return user1 != null;
                })
                .findFirst()
                .orElse(null);
        if (chat1 != null) {
            model.addAttribute("chat", chat1);
            return "redirect:/chat/message?chatId=" + chat1.getId();
        }
        Chat chat = chatService.chatCreate(dto);
        return "redirect:/chat/userchats";
    }

    @GetMapping("/message")
    public String messagePage(Model model, @RequestParam(name = "chatId") String chatId) {
        Chat chat = chatService.getChatById(chatId);
        if (Objects.isNull(chat)) {
            return "redirect:/chat/userchats";
        }
        model.addAttribute("chat", chat);
        return "chat/message";
    }

    @PostMapping("/message")
    public String message(@ModelAttribute ChatUpdateDTO dto, @RequestParam(name = "chatId") String chatId) {
        System.out.println(dto);
        chatService.update(dto, chatId);
        return "redirect:/chat/message?chatId=" + chatId;
    }

}
