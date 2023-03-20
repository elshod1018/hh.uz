package uz.hh.mapper;


import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.hh.domain.Resume;
import uz.hh.dto.CreateResumeDto;


@Mapper(componentModel = "spring")
@Component
public interface ResumeMapper {
    Resume fromCreateDto(CreateResumeDto dto);
}
