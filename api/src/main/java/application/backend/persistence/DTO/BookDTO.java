package application.backend.persistence.DTO;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
public class BookDTO {
    private String title;
    private String author;
    private String type;
    private List<String> genres;
    private String status;  // Changed from Role to status

    public BookDTO() {}

    public BookDTO(String title, String author, String type, List<String> genres, String status) {
        this.title = title;
        this.author = author;
        this.type = type;
        this.genres = genres;
        this.status = status;  // Updated parameter name
    }
}