package uz.hh.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.hh.domain.Message;
import uz.hh.dto.MessageCreateDTO;
import uz.hh.repository.MessageRepository;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;

    public Message create(MessageCreateDTO dto, String ownerId) {
        Message message = Message.builder()
                .ownerId(ownerId)
                .text(dto.getText())
                .build();
        return messageRepository.save(message);
    }

    public Message getById(String messageId) {
        return messageRepository.getMessageById(messageId);
    }
}
