package uz.hh.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Chat {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(generator = "uuid2")
    private String id;
    @Column(nullable = false,name = "candidate_id")
    private String candidateId;
    @Column(nullable = false,name = "vacancy_id")
    private String vacancyId;

    @Column(nullable = false,name = "employer_id")
    private String employerId;
    @Column(nullable = false,name = "message_id")
    private String messageId;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private VacancyStatus status=VacancyStatus.APPLIED;

}

enum VacancyStatus {
    APPLIED, ACCEPT, REJECT
}
