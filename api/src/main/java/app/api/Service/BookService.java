package app.api.Service;

import app.api.Persistence.DTOS.BookDTO;
import app.api.Persistence.Entity.Book;
import app.api.Persistence.Repo.BookRepo;
import app.api.Persistence.mapper.BookMapper;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    BookMapper bookMapper;
    BookRepo bookRepo;

    public BookService(BookMapper bookMapper,BookRepo bookRepo) {
        this.bookMapper = bookMapper;
        this.bookRepo = bookRepo;
    }

    //Adding Book to DB
    public BookDTO persist(BookDTO bookDTO) {
        if (bookDTO == null) {
            throw new IllegalArgumentException("Book DTO cannot be null");
        }

        Book book = bookMapper.toEntity(bookDTO);
        if (book == null) {
            throw new IllegalArgumentException("Invalid book DTO - mapping failed");
        }

        book = bookRepo.save(book);
        return bookMapper.toDto(book);
    }
}
