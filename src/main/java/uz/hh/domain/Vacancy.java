package uz.hh.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Vacancy {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(generator = "uuid2")
    private String id;
    @ManyToOne(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private User owner;
    @Builder.Default
    @Column(name = "is_deleted", nullable = false)
    private boolean is_deleted = false;
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "vacancy")
    private Set<Chat> chats;
    @Column(name = "created_at", nullable = false, columnDefinition = "timestamp default now()")
    @Builder.Default
    @CreationTimestamp
    private LocalDateTime createdAt = LocalDateTime.now();
}
