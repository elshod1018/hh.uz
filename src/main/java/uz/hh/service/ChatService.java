package uz.hh.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import uz.hh.domain.Chat;
import uz.hh.repository.ChatRepository;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;

    public List<Chat> getUserChats(String id) {
        return chatRepository.findAllByCandidateId(id);
    }
}
