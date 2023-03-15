package uz.hh.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.hh.config.security.UserSession;
import uz.hh.domain.*;
import uz.hh.dto.ChatCreateDTO;
import uz.hh.dto.MessageCreateDTO;
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

    @GetMapping("/employerchats")
    @PreAuthorize("hasAnyRole('EMPLOYER')")
    public String employerChatPage(Model model) {
        List<Chat> employerChats = chatService.getEmployerChats(userSession.getId());
        model.addAttribute("chats", employerChats);
        return "userchats";
    }

//    @GetMapping("/create")
//    @PreAuthorize("hasAnyRole('USER')")
//    public String create() {
//
//        return "chat/create";
//    }

    @GetMapping("/create")
    @PreAuthorize("hasAnyRole('USER')")
    public String create(@ModelAttribute ChatCreateDTO dto) {
        chatService.chatCreate(dto, userSession.getUser());
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
                .resumes(new HashSet<>()).roles(new HashSet<>())
                .build();
        Employer employer = new Employer();
        Vacancy vacancy = new Vacancy("1", employer, new HashSet<>(), false, LocalDateTime.now());
        Set<Message> messageSet = Set.of(new Message("1", "1", new Chat(), "Salomalekum", LocalDateTime.now()),
                new Message("2", "1", new Chat(), "Salomalekum", LocalDateTime.now()),
                new Message("2", "1", new Chat(), "Salomalekum", LocalDateTime.now()),
                new Message("3", "1", new Chat(), "Salomalekum", LocalDateTime.now()));
        chat = new Chat("1", user, vacancy, employer,
                messageSet,
                VacancyStatus.APPLIED, LocalDateTime.now(), LocalDateTime.now());
        model.addAttribute("chat", chat);
        return "chat/message";
    }

    @PostMapping("/message")
    public String message(@ModelAttribute MessageCreateDTO dto, @RequestParam(name = "chatId") String chatId) {
        Chat chatById = chatService.getChatById(chatId);
        Set<Message> messages = chatById.getMessages();
        messages.add(Message.builder().text(dto.getText()).ownerId("1").build());
        Chat update = chatService.update(chatById);
        return "redirect:/chat/message?chatId=" + chatId;
    }

}
