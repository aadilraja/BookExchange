package app.api.Persistence.DTOS;

import app.api.Persistence.Entity.Genre;

import java.util.HashSet;

public class BookDTO {

    private long bookId;
    private String bookTitle;
    private String bookAuthor;
    private String bookCategory;
    private HashSet<Genre> genres;

    public BookDTO() {}

    public BookDTO(long bookId,String bookAuthor, String bookCategory, String bookTitle, HashSet<Genre> genres) {
        this.bookAuthor = bookAuthor;
        this.bookCategory = bookCategory;
        this.bookTitle = bookTitle;
        this.genres = genres;
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

    public HashSet<Genre> getGenres() {
        return genres;
    }

    public void setGenres(HashSet<Genre> genres) {
        this.genres = genres;
    }




}
