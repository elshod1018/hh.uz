package uz.hh.dto;

import lombok.*;
import uz.hh.config.enums.*;
import uz.hh.domain.Employer;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VacancyCreateDto {
    private String title;
    private LocalDate application_deadline;
    private EmploymentForm emp_form;
    private EmploymentType emp_type;
    private String region;
    private Language language;
    private LanguageLevel language_level;
    private Market market;
    private double salary;
    private Currency currency;
    private EducationLevel educationLevel;
    private String experienceYear;
    private String description;
    private Employer employer;


}
