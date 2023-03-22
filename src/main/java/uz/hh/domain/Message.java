package uz.hh.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
public class Message  {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(generator = "uuid2")
    private String id;
    @Column(nullable = false, name = "owner_id")
    private String ownerId;
    @ManyToOne
    private Chat chat;
    @Column(nullable = false)
    private String text;

    @CreationTimestamp
    @Column(columnDefinition = "timestamp default now()", name = "created_at", nullable = false)
    private LocalDateTime createdAt;

}
