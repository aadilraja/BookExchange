package app.api.Persistence.Entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "bookId",nullable=false)
    private long bookId;


    //private long userId;

    @Column(name = "bookTitle",nullable=false)
    private String bookTitle;
    @Column(name = "bookAuthor",nullable=false)
    private String bookAuthor;


    @Column(name = "bookCategory",nullable=false)
    private String bookCategory;

    @Column(name = "bookImgPath")
    private String bookImgPath;


    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "book_genres",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genre = new HashSet<>();

    public Book() {
    }

    // --- Helper methods for managing genres ---
    public void addGenre(Genre genre) {
        this.genre.add(genre);
        genre.getBooks().add(this);
    }

    public void removeGenre(Genre genre) {
        this.genre.remove(genre);
        genre.getBooks().remove(this);
    }


//    public String getBookImgPath() {
//        return bookImgPath;
//    }
//
//    public void setBookImgPath(String bookImgPath) {
//        this.bookImgPath = bookImgPath;
//    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }


//    public long getUserId() {
//        return userId;
//    }
//
//    public void setUserId(long userId) {
//        this.userId = userId;
//    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(String bookCategory) {
        this.bookCategory = bookCategory;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }


    public Set<Genre> getGenres() {
        return genre;
    }

    public void setGenres(Set<Genre> genres) {
        this.genre = genres;
    }

}
