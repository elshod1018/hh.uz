package uz.hh.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.Set;

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

    @Column(name = "is_read" , columnDefinition = "boolean")
    Boolean isRead;
    @Column(name = "message" , columnDefinition = "text")
    String message;

}


