package application.backend.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class FileService {

    public String saveCoverImg(MultipartFile file) throws IOException {
        final String dirPath="resources/COVER_IMG/";


        File dir = new File(dirPath);
        if (!dir.exists()) dir.mkdirs();


        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        String filePath = dirPath + fileName;

        Files.write(Paths.get(filePath), file.getBytes());

        return filePath;
    }
}
