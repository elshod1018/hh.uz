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
    public String userChatPage(Model model) {
        List<Chat> userChats = chatService.getUserChats(userSession.getId());
        model.addAttribute("chats", userChats);
        return "chat/userchats";
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('USER')")
    public String create(@ModelAttribute ChatCreateDTO dto) {
        Chat chat = chatService.chatCreate(dto);
        return "redirect:/chat/userchats";
    }

    @GetMapping("/message")
    public String messagePage(Model model, @RequestParam(name = "chatId") String chatId) {
        Chat chat = chatService.getChatById(chatId);
        System.out.println(chat + " : " + chatId);
        if (Objects.isNull(chat)) {
            return "redirect:/chat/userchats";
        }
        model.addAttribute("chat", chat);
        return "chat/message";
    }

    @PostMapping("/message")
    public String message(@ModelAttribute ChatUpdateDTO dto, @RequestParam(name = "chatId") String chatId) {
        chatService.update(dto, chatId);
        return "redirect:/chat/message?chatId=" + chatId;
    }

}
