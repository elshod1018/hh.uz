package uz.hh.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Vacancy {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(generator = "uuid2")
    private String id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDateTime application_deadline;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private EmploymentForm emp_form = EmploymentForm.IN_OFFICE;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private EmploymentType emp_type = EmploymentType.FULL_TIME;

    @Column(nullable = false)
    private String country;

    @Column
    private String language;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LanguageLevel language_level = LanguageLevel.NATIVE;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Market market;

//    @Column
//    private String [] skills; //string array bolishi kk

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
    private EducationLevel educationLevel= EducationLevel.NONE;

    @Column
    private String experienceYear;

    @Column
    private String description;


    @ManyToOne
    @JoinColumn(name = "employer_id", nullable = false)
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

    enum EmploymentForm {
        REMOTE, IN_OFFICE, HYBRID
    }

    enum EmploymentType {
        FULL_TIME, PART_TIME, FREELANCE, INTERNSHIP
    }

    enum LanguageLevel {
        BEGINNER, INTERMEDIATE, UPPER_INTERMEDIATE, NATIVE, PROFICIENT
    }

    enum Market {
        INFORMATION_TECHNOLOGY,
        SOFTWARE_DEVELOPMENT,
        MANAGEMENT,
        DESIGN,
        WEB_DEVELOPMENT,
        EDUCATION,
        SALES,
        MARKETING,
        TRAVEL,
        DEVOPS,
        ART,
        SCIENCE

    }

    enum Currency {
        DOLLAR, EURO, SUM
    }

    enum EducationLevel {
        BACHELORS, MASTERS, NONE, HIGH_SCHOOL,
    }


}
