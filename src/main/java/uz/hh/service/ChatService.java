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
                .status(VacancyStatus.APPLIED)
                .build();
        User user = userSession.getUser();
        User employer = userService.findById(vacancy.getEmployer().getId());
        chat.getUsers().add(user);
        chat.getUsers().add(employer);
        user.getChats().add(chat);
        employer.getChats().add(chat);
        chat = chatRepository.save(chat);
        userService.update(employer);
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

    public List<Chat> getChatByVacancyId(String vacancyId) {
        return chatRepository.findAllByVacancyId(vacancyId);
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
            chatById = chatRepository.save(chatById);
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
        return chatRepository.findAllByUserId(userId);
    }
}
