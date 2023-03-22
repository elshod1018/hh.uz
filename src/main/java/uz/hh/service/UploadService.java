package uz.hh.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.hh.domain.Upload;
import uz.hh.dto.UploadCreateDTO;
import uz.hh.repository.UploadRepository;
import uz.hh.utils.BaseUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Service
public class UploadService {
    private Path rootPath;
    private final UploadRepository uploadRepository;

    public UploadService(UploadRepository uploadRepository) {
        this.uploadRepository = uploadRepository;
    }

    @PostConstruct
    public void init() throws IOException {
        rootPath = Path.of(System.getProperty("user.home"), "/uploads");
        if (!Files.exists(rootPath)) {
            Files.createDirectories(rootPath);
        }
    }

    public String create(UploadCreateDTO dto) {
        return createAndGet(dto).getId();
    }

    public Upload createAndGet(UploadCreateDTO dto) {
        MultipartFile file = dto.getFile();
        Upload upload = Upload.builder()
                .contentType(file.getContentType())
                .size(file.getSize())
                .originalName(file.getOriginalFilename())
                .generatedName(BaseUtils.generateUniqueName(Objects.requireNonNull(file.getOriginalFilename())))
                .build();
        uploadRepository.save(upload);
        CompletableFuture.runAsync(() -> {
            Path path = rootPath.resolve(upload.getGeneratedName());
            try {
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return upload;
    }
}
