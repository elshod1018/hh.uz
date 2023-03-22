package uz.hh.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.hh.domain.Upload;
import uz.hh.repository.UploadRepository;

import java.nio.file.Path;

@Controller
public class FileController {
    private final UploadRepository uploadRepository;
    private Path rootPath = Path.of(System.getProperty("user.home"), "apps/uploads");

    public FileController(UploadRepository uploadRepository) {
        this.uploadRepository = uploadRepository;
    }

    @GetMapping("/download/{id:.+}")
    public ResponseEntity<Resource> homePage(@PathVariable String id) {
        Upload upload = uploadRepository.findByGeneratedName(id);
        Resource resource = new FileSystemResource(rootPath.resolve(id));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(upload.getContentType()))
                .contentLength(upload.getSize())
                .body(resource);
    }
}
