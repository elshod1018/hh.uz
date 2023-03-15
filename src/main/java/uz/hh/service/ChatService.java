package uz.hh.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.hh.domain.*;
import uz.hh.dto.ChatCreateDTO;
import uz.hh.dto.ChatUpdateDTO;
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
        Set<Message> messages = Set.of(messageService.getById(dto.getMessageId()));
        Chat chat = Chat.builder()
                .vacancy(vacancy)
                .users(Set.of(vacancy.getOwner(), user))
                .messages(messages)
                .status(VacancyStatus.APPLIED)
                .build();
        chatRepository.save(chat);
        return chat;
    }

    public Chat getChatById(String chatId) {
        Optional<Chat> chatById = chatRepository.findChatById(chatId);
        return chatById.orElse(null);
    }


    public Chat update(ChatUpdateDTO dto, String chatId) {
        Chat chatById = getChatById(chatId);
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

    public List<Chat> getUserChats(String userId) {
        return chatRepository.findAllByUsersId(userId);
    }
}
