package app.api.Service;

import app.api.Persistence.DTOS.BookDTO;
import app.api.Persistence.Entity.Book;
import app.api.Persistence.Repo.BookRepo;
import app.api.Service.mapper.BookMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.UnsupportedMediaTypeStatusException;

import java.io.IOException;
import java.util.List;

@Service
public class BookService {
    BookMapper bookMapper;
    BookRepo bookRepo;
    FileService fileService;

    public BookService(BookMapper bookMapper,BookRepo bookRepo,FileService fileService) {
        this.bookMapper = bookMapper;
        this.bookRepo = bookRepo;
        this.fileService = fileService;
    }



    //Adding Book to DB
    public BookDTO persist(BookDTO bookDTO,MultipartFile imgFile) throws IOException,
                                                                         IllegalArgumentException,
                                                                         UnsupportedMediaTypeStatusException {
        if (bookDTO == null||imgFile==null) {
            throw new IllegalArgumentException("Request cannot be null");
        }

        String imgUrl=fileService.uploadImage(imgFile);

        Book book = bookMapper.toEntity(bookDTO);
        if (book == null) {
            throw new IllegalArgumentException("Invalid book DTO - mapping failed");
        }

        book.setImageUrl(imgUrl);

        book=bookRepo.save(book);


       return bookMapper.toDto(book);
    }


    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepo.findAll();

        List<BookDTO> bookDTOS = books.stream()
                .map(bookMapper::toDto)
                .toList();

        if (bookDTOS.isEmpty()) {
            throw new EntityNotFoundException("No books found");
        }

        return bookDTOS;
    }

    public BookDTO getBookById(Long id) {
        Book book = bookRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id));

        return bookMapper.toDto(book);
    }
}
