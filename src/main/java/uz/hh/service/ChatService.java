package uz.hh.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.hh.config.security.UserSession;
import uz.hh.domain.*;
import uz.hh.dto.ChatCreateDTO;
import uz.hh.dto.ChatUpdateDTO;
import uz.hh.dto.MessageCreateDTO;
import uz.hh.enums.VacancyStatus;
import uz.hh.repository.ChatRepository;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;
    private final VacancyService vacancyService;
    private final UserSession userSession;
    private final MessageService messageService;
    private final UserService userService;

    public Chat chatCreate(ChatCreateDTO dto) {
        Vacancy vacancy = vacancyService.getById(dto.getVacancyId());
        Chat chat = Chat.builder()
                .vacancy(vacancy)
//                .users(Set.of(vacancy.getEmployer(), userSession.getUser()))
                .status(VacancyStatus.APPLIED)
                .build();
        chat = chatRepository.save(chat);
        User user = userSession.getUser();
        user.getChats().add(chat);
        userService.update(user);
        MessageCreateDTO messageCreateDTO = MessageCreateDTO.builder()
                .chat(chat)
                .ownerId(userSession.getId())
                .text(dto.getText())
                .build();
        messageService.create(messageCreateDTO);
        return chat;
    }

    public Chat getChatById(String chatId) {
        return chatRepository.findById(chatId).orElse(null);
    }

    public Chat getChatByVacancyId(String vacancyId) {
        return chatRepository.findByVacancy_Id(vacancyId);
    }

    public boolean update(ChatUpdateDTO dto, String chatId) {
        Chat chatById = getChatById(chatId);
        if (chatById != null) {
            chatById.setUpdatedAt(LocalDateTime.now());
            String dtoStatus = dto.getStatus();
            if (dtoStatus != null) {
                VacancyStatus status = VacancyStatus.valueOf(dtoStatus.toUpperCase());
                chatById.setStatus(status);
            }
            MessageCreateDTO messageCreateDTO = MessageCreateDTO.builder()
                    .text(dto.getText())
                    .chat(chatById)
                    .ownerId(userSession.getId())
                    .build();
            messageService.create(messageCreateDTO);
            return true;
        }
        return false;
    }

    public List<Chat> getUserChats() {
        String userId = userSession.getId();
        return chatRepository.findByUsers_Id(userId);
    }
}
