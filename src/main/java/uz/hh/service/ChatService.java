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
    private final MessageService messageService;
    private final UserSession userSession;

    public Chat chatCreate(ChatCreateDTO dto) {
        Vacancy vacancy = vacancyService.getById(dto.getVacancyId());
        System.out.println(dto);
        System.out.println(vacancy);
        MessageCreateDTO messageCreateDTO = MessageCreateDTO.builder()
                .text(dto.getText())
                .build();
        Set<Message> messages = Set.of(messageService.create(messageCreateDTO, userSession.getId()));
        System.out.println(messages);
        Chat chat = Chat.builder()
                .vacancy(vacancy)
                .users(Set.of(vacancy.getEmployer(), userSession.getUser()))
                .messages(messages)
                .status(VacancyStatus.APPLIED)
                .build();
        vacancy.getChats().add(chat);
        chat = chatRepository.save(chat);
        System.out.println(chat);
        return chat;
    }

    public Chat getChatById(String chatId) {
        return chatRepository.findById(chatId).orElse(null);
    }

    public Chat getChatByVacancyId(String vacancyId) {
        return chatRepository.findByVacancy_Id(vacancyId);
    }


    public Chat update(ChatUpdateDTO dto, String chatId) {
        Chat chatById = getChatById(chatId);
        if (chatById != null) {
            chatById.setUpdatedAt(LocalDateTime.now());
            Message message = Message.builder()
                    .text(dto.getText())
                    .build();
            chatById.getMessages().add(message);
            String dtoStatus = dto.getStatus();
            if (dtoStatus != null) {
                VacancyStatus status = VacancyStatus.valueOf(dtoStatus.toUpperCase());
                chatById.setStatus(status);
            }
            return chatRepository.save(chatById);
        }
        return null;
    }

    public List<Chat> getUserChats() {
        String userId = userSession.getId();
        return chatRepository.findByUsers_Id(userId);
    }
}
