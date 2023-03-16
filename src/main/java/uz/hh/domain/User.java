package uz.hh.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import uz.hh.enums.Role;
import uz.hh.enums.Status;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
@Builder
public class User {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(generator = "uuid2")
    @Column(columnDefinition = "varchar default gen_random_uuid()")
    private String id;
    @Column(nullable = false)
    private String fullName;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;
    @Column(nullable = false)
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;
    @ManyToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_chats",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "chat_id", referencedColumnName = "id")
    )
    private Set<Chat> chats;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "user",
            fetch = FetchType.EAGER)
    private Set<Resume> resumes;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "employer")
    private Set<Vacancy> vacancies;
    @Column(name = "region", nullable = false)
    private String region;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Builder.Default
    @Column(name = "is_deleted", nullable = false)
    private boolean is_deleted = false;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, columnDefinition = "timestamp default now()")
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

}


