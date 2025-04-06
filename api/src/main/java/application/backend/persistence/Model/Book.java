package application.backend.persistence.Model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_id", nullable = false)
    private Long bookId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String coverImgPath;

    @Column(nullable = false)
    private String status;  // Changed from Role to status

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "book_genres", joinColumns = @JoinColumn(name = "book_id"))
    @Column(name = "genre", nullable = false)
    private List<String> genres = new ArrayList<>();

    public Book() {}

    public Book(String title, String author, String coverImgPath, String type,
                List<String> genres, String status) {
        this.title = title;
        this.author = author;
        this.type = type;
        this.coverImgPath = coverImgPath;
        this.status = status;  // Updated parameter name
        this.genres = genres;
    }
}