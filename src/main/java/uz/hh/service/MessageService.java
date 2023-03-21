package uz.hh.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.hh.domain.Chat;
import uz.hh.domain.Message;
import uz.hh.dto.MessageCreateDTO;
import uz.hh.repository.MessageRepository;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;

    public Message create(MessageCreateDTO dto) {
        Message message = Message.builder()
                .ownerId(dto.getOwnerId())
                .text(dto.getText())
                .chat(dto.getChat())
                .build();
        return messageRepository.save(message);
    }

    public Message getById(String messageId) {
        return messageRepository.
                findById(messageId)
                .orElse(null);
    }
}
