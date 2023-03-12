package uz.hh.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Vacancy {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(generator = "uuid2")
    private String id;
    @Column(name = "owner_id", nullable = false)
    private String ownerId;



    @Builder.Default
    @Column(name = "is_deleted", nullable = false)
    private boolean is_deleted = false;
    @Column(name = "created_at", nullable = false, columnDefinition = "timestamp default now()")
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
}
