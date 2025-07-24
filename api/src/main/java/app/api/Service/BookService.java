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


    public BookDTO persist(BookDTO bookDTO) {

        try {
            Book book = bookMapper.toEntity(bookDTO);
            if(book==null)
            {
                throw new IllegalArgumentException("Invalid book DTO");
            }

           book= bookRepo.save(book);

            return bookMapper.toDto(book);


        }catch (Exception e) {
            throw e;


        }

    }
}
