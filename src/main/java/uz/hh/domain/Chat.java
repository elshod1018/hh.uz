package uz.hh.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.service.annotation.GetExchange;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Chat {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(generator = "uuid2")
    private String id;
    @ManyToOne(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            optional = false)
    private User candidate;

    @ManyToOne(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            optional = false)
    private Vacancy vacancy;

    @ManyToOne(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            optional = false)
    private Employer employer;
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private Set<Message> messages;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private VacancyStatus status = VacancyStatus.APPLIED;

    @Builder.Default
    @Column(nullable = false, name = "created_at", columnDefinition = "timestamp default now()")
    LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at", columnDefinition = "timestamp")
    LocalDateTime updatedAt;

}


