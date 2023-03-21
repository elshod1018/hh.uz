package uz.hh.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.hh.domain.Chat;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageCreateDTO {
    private Chat chat;
    private String text;
    private String ownerId;

}
