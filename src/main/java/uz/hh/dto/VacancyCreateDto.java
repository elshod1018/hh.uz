package uz.hh.dto;

import lombok.*;
import uz.hh.enums.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
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
}
