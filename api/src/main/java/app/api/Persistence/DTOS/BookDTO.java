package app.api.Persistence.DTOS;

import app.api.Persistence.Entity.Genre;

import java.util.HashSet;
import java.util.Set;

public class BookDTO {

    private long bookId;
    private String bookTitle;
    private String bookAuthor;
    private String bookCategory;
    private Set<GenreDTO> genre = new HashSet<>();
    public BookDTO() {}

    public BookDTO(String bookTitle, String bookAuthor, String bookCategory, Set<GenreDTO> genre) {
        this.bookAuthor = bookAuthor;
        this.bookCategory = bookCategory;
        this.bookTitle = bookTitle;
        this.genre = genre;
        this.bookId = bookId;
    }

    public long getBookId() {
        return bookId;
    }
    public void setBookId(long bookId) {
        this.bookId = bookId;
    }


    public String getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(String bookCategory) {
        this.bookCategory = bookCategory;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public Set<GenreDTO> getGenre() {
        return genre;
    }

    public void setGenre(Set<GenreDTO> genre) {
        this.genre = genre;
    }







}
