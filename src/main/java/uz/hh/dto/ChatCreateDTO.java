package uz.hh.dto;

import uz.hh.domain.VacancyStatus;

public record ChatCreateDTO(String candidateId, String vacancyId,
                            String employerId, String messageId, VacancyStatus status) {
}
