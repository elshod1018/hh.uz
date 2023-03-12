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
@Entity
@Builder
public class Message {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(generator = "uuid2")
    private String id;
    @Column(nullable = false,name = "owner_id")
    private String ownerId;
    @Column(nullable = false)
    private String text;
    @Builder.Default
    @Column(columnDefinition = "timestamp default now()", name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

}
