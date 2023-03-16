package uz.hh.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.hh.domain.*;
import uz.hh.dto.ChatCreateDTO;
import uz.hh.dto.ChatUpdateDTO;
import uz.hh.dto.MessageCreateDTO;
import uz.hh.enums.VacancyStatus;
import uz.hh.repository.ChatRepository;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;
    private final VacancyService vacancyService;
    private final MessageService messageService;

    public Chat chatCreate(ChatCreateDTO dto, User user) {
        Vacancy vacancy = vacancyService.getById(dto.getVacancyId());
        MessageCreateDTO messageCreateDTO = MessageCreateDTO.builder()
                .text(dto.getText())
                .build();
        Set<Message> messages = Set.of(messageService.create(messageCreateDTO, user.getId()));
        Chat chat = Chat.builder()
                .vacancy(vacancy)
                .users(Set.of(vacancy.getEmployer(), user))
                .messages(messages)
                .status(VacancyStatus.APPLIED)
                .build();
        chatRepository.save(chat);
        return chat;
    }

    public Chat getChatById(String chatId) {
        return chatRepository.findChatById(chatId).orElse(null);
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

    public List<Chat> getUserChats(String userId) {
        return chatRepository.findAllByUsersId(userId);
    }
}
