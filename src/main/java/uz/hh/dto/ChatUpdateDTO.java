package uz.hh.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import uz.hh.enums.VacancyStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatUpdateDTO {
    private String text;
    private String status;
}
