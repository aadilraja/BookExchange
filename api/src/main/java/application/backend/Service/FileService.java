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
        final String dirPath = "C:\\Project\\BookExchange\\api\\src\\main\\resources\\COVER_IMG\\";

        String filePath = null;
        try {
            File dir = new File(dirPath);
            if (!dir.exists()) dir.mkdirs();


            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            filePath = dirPath + fileName;

            Files.write(Paths.get(filePath), file.getBytes());


        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return filePath;

    }
}
