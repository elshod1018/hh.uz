package uz.hh.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.hh.config.security.UserSession;
import uz.hh.domain.Chat;
import uz.hh.dto.ChatCreateDTO;
import uz.hh.repository.ChatRepository;
import uz.hh.service.ChatService;

import java.util.List;

@Controller
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {
    private final UserSession userSession;
    private final ChatService chatService;
    private final ChatRepository chatRepository;

    @GetMapping("/mychats")
    public String myChatPage(Model model) {
        List<Chat> userChats = chatService.getUserChats(userSession.getId());
        model.addAttribute("chats", userChats);
        return "chat/mychats";
    }

    @GetMapping("/my-chats-unread")
    public String unreadChatsPage(@ModelAttribute ChatCreateDTO dto, Model model) {
        List<Chat> userChats = chatService.getUserChats(userSession.getId());
        model.addAttribute("unreadchats", userChats);
        return "chat/my-chats-unread";

    }

    @PostMapping("/newchat")
    public String newChatPage(@ModelAttribute ChatCreateDTO dto, Model model) {
        Chat newchat = Chat.builder()
                .candidate(chatService.getCandidateById(dto.candidateId()))
                .vacancy(chatService.getVacancyById(dto.vacancyId()))
                .employer(chatService.getEmployerById(dto.employerId()))
                .messages(chatService.getAllMessagesByMessageId(dto.messageId()))
                .status(dto.status())
                .isRead(dto.isRead())
                .build();
        model.addAttribute("newchat", newchat);
        chatRepository.save(newchat);
        return "redirect:/mychats";
    }

    @PostMapping("/answer")
    public String answerChatPage(@ModelAttribute ChatCreateDTO dto, Model model) {
        Chat newchat = Chat.builder()
                .candidate(chatService.getCandidateById(dto.candidateId()))
                .vacancy(chatService.getVacancyById(dto.vacancyId()))
                .employer(chatService.getEmployerById(dto.employerId()))
                .messages(chatService.getAllMessagesByMessageId(dto.messageId()))
                .status(dto.status())
                .isRead(dto.isRead())
                .message(dto.message())
                .build();
        model.addAttribute("answer", newchat);

        chatRepository.save(newchat);
        return "redirect:/mychats";
    }


}
