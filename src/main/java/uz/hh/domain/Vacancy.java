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
public class Vacancy {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(generator = "uuid2")
    private String id;

    private String title;
    private LocalDateTime application_dedline;
    private EmploymentForm emp_form;

    private EmploymentType emp_type;

    private  String country;

    private String language;
    private LanguageLevel language_level;

    private Market market;

    private String  skills; //string array bolishi kk

    private double salary;
    private Currency currency;

    private EducationLevel educationLevel;

    private String experienceYear;

    private String description;


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

    enum EmploymentForm{
        REMOTE, IN_OFFICE, HYBRID
    }

    enum EmploymentType{
        FULL_TIME, PART_TIME, FREELANCE, INTERNSHIP
    }

    enum LanguageLevel{
        BEGINNER, INTERMEDIATE, UPPER_INTERMEDIATE, NATIVE, PROFICIENT
    }

    enum Market{
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

    enum Currency{
        DOLLAR, EURO, SUM
    }

    enum EducationLevel{
        BACHELORS, MASTERS, NONE, HIGH_SCHOOL,
    }


}
