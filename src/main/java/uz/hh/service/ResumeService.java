package uz.hh.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.hh.domain.Resume;
import uz.hh.repository.ResumeRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResumeService {
    private final ResumeRepository resumeRepository;

    public Resume findResumesByUser_Id(String id) {
        return resumeRepository.findByUser_Id(id);
    }

    public Resume findById(String id) {
        Optional<Resume> optionalResume = resumeRepository.findById(id);
        return optionalResume.orElse(null);
    }
}
