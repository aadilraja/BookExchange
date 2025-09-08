package app.api.Service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.UnsupportedMediaTypeStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class FileService {

    private static final List<String> ALLOWED_TYPES =
            Arrays.asList("image/jpeg", "image/png");

    @Value("${dir.upload.image}")
    private String uploadDir;

    @Value("${app.base.url}")
    private String baseUrl;
    public FileService() {}
    @PostConstruct
    public void init() throws IOException {
        // Now, uploadDir is guaranteed to have its value
        Files.createDirectories(Paths.get(uploadDir));
    }

    public String uploadImage(MultipartFile file) throws IOException {

            validateImage(file);
            String fileName=generateFileName(file.getOriginalFilename());
            Path uploadPath= Paths.get(uploadDir);

            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream() ,filePath, StandardCopyOption.REPLACE_EXISTING);

        return  filePath.toString();

    }

    private String generateFileName(String originalFilename) {

        String sanitizedFilename = originalFilename
                                    .toLowerCase()
                                    .trim()
                                    .replaceAll("\\s+", "_")
                                    .replaceAll("[^a-z0-9._-]", "_")
                                    .replaceAll("_+", "_");

        return UUID.randomUUID()+"_"+sanitizedFilename;
    }

    private void validateImage(MultipartFile img) {
        if(img.isEmpty() || img.getSize() == 0)
        {
            throw new IllegalArgumentException("Image file is required");

        }
        if(!ALLOWED_TYPES.contains(img.getContentType()))
        {
            throw new UnsupportedMediaTypeStatusException("Only JPEG, PNG, and GIF are allowed");
        }

    }

    public void DeleteFileAtPath(String imagePath) throws IOException {
        Path path = Paths.get(imagePath);
        if(Files.exists(path))
        {
            Files.delete(path);
        }


    }
    public String extractFileName(String fullPath) {
        return Paths.get(fullPath).getFileName().toString();
    }
}
