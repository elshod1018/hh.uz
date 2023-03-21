package uz.hh.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import uz.hh.domain.Resume;
import uz.hh.dto.CreateResumeDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-21T10:20:06+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.6 (Private Build)"
)
@Component
public class ResumeMapperImpl implements ResumeMapper {

    @Override
    public Resume fromCreateDto(CreateResumeDto dto) {
        if ( dto == null ) {
            return null;
        }

        Resume.ResumeBuilder resume = Resume.builder();

        resume.firstName( dto.getFirstName() );
        resume.lastName( dto.getLastName() );
        resume.email( dto.getEmail() );
        resume.address( dto.getAddress() );
        resume.phoneNumber( dto.getPhoneNumber() );
        resume.job( dto.getJob() );
        resume.profile( dto.getProfile() );
        resume.experience( dto.getExperience() );
        resume.tech_skill( dto.getTech_skill() );

        return resume.build();
    }
}
