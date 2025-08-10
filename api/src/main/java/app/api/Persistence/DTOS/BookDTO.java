package app.api.Persistence.DTOS;

import java.util.HashSet;
import java.util.Set;

public class BookDTO {

    private Long id;
    // private long userId;
    private String title;
    private String author;
    private String category;
     private String imageUrl;
    private Set<GenreDTO> genres = new HashSet<>();

    public BookDTO() {}

    public BookDTO(String title, String author, String category, Set<GenreDTO> genres) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.genres = genres;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    // public long getUserId() {
    //     return userId;
    // }

    // public void setUserId(long userId) {
    //     this.userId = userId;
    // }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

     public String getImageUrl() {
         return imageUrl;
     }

     public void setImageUrl(String imageUrl) {
         this.imageUrl = imageUrl;
     }

    public Set<GenreDTO> getGenres() {
        return genres;
    }

    public void setGenres(Set<GenreDTO> genres) {
        this.genres = genres;
    }
}