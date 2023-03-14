package uz.hh.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import uz.hh.domain.Chat;
import uz.hh.domain.User;
import uz.hh.domain.VacancyStatus;
import uz.hh.dto.ChatCreateDTO;
import uz.hh.repository.ChatRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;


    public List<Chat> getUserChats(String id) {
        return chatRepository.findAllByCandidateId(id);
    }

    public List<Chat> getEmployerChats(String id) {
        return chatRepository.findAllByEmployerId(id);
    }

    public Chat chatCreate(ChatCreateDTO dto, User user) {
        Chat chat = Chat.builder()
                .vacancy(dto.vacancy())
                .candidate(user)
                .employer(dto.employer())
                .messages(Set.of(dto.message()))
                .status(VacancyStatus.APPLIED)
                .build();
        chatRepository.save(chat);
        return chat;
    }

    public Chat getChatById(String chatId) {
        return chatRepository.getChatById(chatId);
    }
}
