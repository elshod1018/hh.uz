package uz.hh.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Chat {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(generator = "uuid2")
    private String id;
    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private User candidate;
    @Column(nullable = false, name = "vacancy_id")
    private String vacancyId;

    @Column(nullable = false, name = "employer_id")
    private String employerId;
    @Column(nullable = false, name = "message_id")
    private String messageId;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private VacancyStatus status = VacancyStatus.APPLIED;

    @Builder.Default
    @Column(nullable = false, name = "created_at", columnDefinition = "timestamp default now()")
    LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at",columnDefinition = "timestamp")
    LocalDateTime updatedAt;

}


