package uz.hh.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import uz.hh.enums.VacancyStatus;

import java.time.LocalDateTime;
import java.util.*;

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
    @ManyToMany(mappedBy = "chats", fetch = FetchType.EAGER)
    @Builder.Default
    private Set<User> users = new HashSet<>();

    @ManyToOne
    private Vacancy vacancy;
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "chat")
    @Builder.Default
    private Set<Message> messages = new HashSet<>();
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

}


