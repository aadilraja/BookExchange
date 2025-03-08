package application.backend.utils;

import application.backend.persistence.DTO.BookDTO;
import application.backend.persistence.Model.Book;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public  Book toBook(BookDTO bookdto) {
        Book book = new Book();
        book.setTitle(bookdto.getTitle());
        book.setAuthor(bookdto.getAuthor());
        book.setType(bookdto.getType());
//        book.setUser_id(bookdto.getUser_id());
        book.setGenres(bookdto.getGenres());
        return book;
    }

    public BookDTO toDTO(Book book) {
        BookDTO bookDto= new BookDTO();
        bookDto.setTitle(book.getTitle());
        bookDto.setType(book.getType());
        bookDto.setAuthor(book.getAuthor());
//        bookDto.setUser_id(book.getUser_id());
        bookDto.setGenres(book.getGenres());
        return bookDto;
    }
}
