package application.backend.utils;

import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

public class Image {

    public MediaType getMediaType(String fileType) {
        Map<String, MediaType> mediaTypes = new HashMap<>();

        mediaTypes.put("jpg", MediaType.IMAGE_JPEG);
        mediaTypes.put("jpeg", MediaType.IMAGE_JPEG);
        mediaTypes.put("png", MediaType.IMAGE_PNG);
        mediaTypes.put("gif", MediaType.IMAGE_GIF);
        mediaTypes.put("bmp", MediaType.valueOf("image/bmp"));
        mediaTypes.put("webp", MediaType.valueOf("image/webp"));
        mediaTypes.put("tiff", MediaType.valueOf("image/tiff"));
        mediaTypes.put("svg", MediaType.valueOf("image/svg+xml"));

        // Example: Print the MediaType for PNG
        System.out.println("MediaType for PNG: " + mediaTypes.get("png"));
        return mediaTypes.get(fileType);
    }
}


