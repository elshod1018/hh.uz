package uz.hh.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import uz.hh.enums.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
public class Vacancy {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(generator = "uuid2")
    private String id;
    @Builder.Default
    @Column(name = "is_deleted", columnDefinition = "boolean default false", nullable = false)
    private boolean is_deleted = false;
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "vacancy")
    @Builder.Default
    private Set<Chat> chats = new HashSet<>();
    @Column(name = "created_at", nullable = false, columnDefinition = "timestamp default now()")
    @Builder.Default
    @CreationTimestamp
    private LocalDateTime createdAt = LocalDateTime.now();
    @Column(name = "company_name")
    private String companyName;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private LocalDate application_deadline;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private EmploymentForm emp_form = EmploymentForm.IN_OFFICE;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private EmploymentType emp_type = EmploymentType.FULL_TIME;
    @Column(nullable = false)
    private String region;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Language language = Language.ENGLISH;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private LanguageLevel language_level = LanguageLevel.NATIVE;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Market market;
    @Column(nullable = false)
    @Builder.Default
    private double salary = 0;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Currency currency = Currency.SUM;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private EducationLevel educationLevel = EducationLevel.NONE;
    @Column
    private String experienceYear;
    @Column
    private String description;
    @ManyToOne
    private User employer;

}
