package uz.hh.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.hh.domain.Chat;
import uz.hh.domain.Message;
import uz.hh.domain.User;
import uz.hh.domain.VacancyStatus;
import uz.hh.dto.ChatCreateDTO;
import uz.hh.repository.MessageRepository;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;

    public Message getById(String messageId) {
        return messageRepository.getMessageById(messageId);
    }
}
