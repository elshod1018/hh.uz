package uz.hh.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import uz.hh.enums.VacancyStatus;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Chat {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(generator = "uuid2")
    private String id;
    @ManyToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,mappedBy = "chats")
    private Set<User> users;

    @ManyToOne(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private Vacancy vacancy;
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "chat")
    private Set<Message> messages;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private VacancyStatus status = VacancyStatus.APPLIED;

    @Builder.Default
    @CreationTimestamp
    @Column(nullable = false, name = "created_at", columnDefinition = "timestamp default now()")
    LocalDateTime createdAt = LocalDateTime.now();
    @CreationTimestamp
    @Column(name = "updated_at", columnDefinition = "timestamp")
    LocalDateTime updatedAt;

    @Column(name = "is_read" , columnDefinition = "boolean")
    Boolean isRead;
    @Column(name = "message" , columnDefinition = "text")
    String message;

}


