package application.backend.persistence.DTO;


import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class BookDTO {
    private String title;
    private String author;
    private List<String> genres;
    private String user_id;

    public BookDTO(){}

    public BookDTO( String title,String author, String user_id, List<String> genres) {
        this.title = title;
        this.author = author;
        this.user_id = user_id;
        this.genres = genres;
    }

}
