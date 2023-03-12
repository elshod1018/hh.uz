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
@Builder
public class Vacancy {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(generator = "uuid2")
    private String id;
    @ManyToOne
    @JoinColumn(name = "employer_id",nullable = false)
    private Employer employer;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private Set<Chat> chats;


    @Builder.Default
    @Column(name = "is_deleted", nullable = false)
    private boolean is_deleted = false;
    @Column(name = "created_at", nullable = false, columnDefinition = "default now()")
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
}
