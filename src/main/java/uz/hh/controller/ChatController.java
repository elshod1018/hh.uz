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

    @GetMapping("/create")
    @PreAuthorize("hasAnyRole('USER')")
    public String create(@ModelAttribute ChatCreateDTO dto) {
        Chat chat = chatService.chatCreate(dto, userSession.getUser());
        return "redirect:/chat/userchats";
    }

    @GetMapping("/message")
    public String messagePage(Model model, @RequestParam(name = "chatId", required = false) String chatId) {
        Chat chat = chatService.getChatById(chatId);
        System.out.println(chat + " : " + chatId);
//        if (Objects.isNull(chat)) {
//            return "redirect:/chat/userchats";
//        }
        User user = User.builder()
                .id("1").email("e")
                .password("1")
                .username("e")
                .fullName("e")
                .status(Status.ACTIVE)
                .role(Role.USER)
                .resumes(new HashSet<>())
                .build();
        Vacancy vacancy = Vacancy.builder().id("1").employer(user).build();
        Message message1 = Message.builder().id("1").text("Boy ota").ownerId("1").build();
        Message message2 = Message.builder().id("2").text("Boy ota").ownerId("1").build();
        Message message3 = Message.builder().id("3").text("Boy ota").ownerId("1").build();
        Message message4 = Message.builder().id("4").text("Boy ota").ownerId("1").build();
        Set<Message> messageSet = Set.of(message1, message2, message3, message4);
        User employer = User.builder().role(Role.EMPLOYER).id("2").build();
        chat = new Chat("1", Set.of(user, employer), vacancy,
                messageSet,
                VacancyStatus.APPLIED,
                LocalDateTime.now(),
                LocalDateTime.now());
        model.addAttribute("chat", chat);
        return "chat/message";
    }

    @PostMapping("/message")
    public String message(@ModelAttribute ChatUpdateDTO dto, @RequestParam(name = "chatId") String chatId) {
        Chat update = chatService.update(dto, chatId);
        return "redirect:/chat/message?chatId=" + chatId;
    }

}
