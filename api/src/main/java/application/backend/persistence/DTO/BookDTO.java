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
    private String role;
//    private String user_id;

    public BookDTO(){}

    public BookDTO( String title,String author,String type, List<String> genres,String role) {
        this.title = title;
        this.author = author;
        this.type=type;
//        this.user_id = user_id;
        this.genres = genres;
        this.role=role;
    }

}
