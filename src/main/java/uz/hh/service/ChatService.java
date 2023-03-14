package uz.hh.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import uz.hh.domain.*;
import uz.hh.repository.ChatRepository;

import java.util.List;
import java.util.Set;
@Service
@Configuration
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;

    public User getCandidateById(String id) {
        return chatRepository.findByCandidateId(id);
    }

    public List<Chat> getUserChats(String id) {
        return chatRepository.findAllByCandidateId(id);
    }


    public List<Chat> getUserChatsUnread(String id, Boolean isRead) {
        return (List<Chat>) chatRepository.findAllByCandidateIdAndIsRead(id, isRead);
    }


    public Vacancy getVacancyById(String vacancyId) {
        return chatRepository.findByVacancyId(vacancyId);
    }

    public Employer getEmployerById(String employerId) {
        return chatRepository.findByEmployerId(employerId);
    }

    public Set<Message> getAllMessagesByMessageId(String messageId) {
        return chatRepository.findAllMessagesByMessageId(messageId);
    }
}
