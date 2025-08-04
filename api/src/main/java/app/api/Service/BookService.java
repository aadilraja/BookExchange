package app.api.Service;

import app.api.Persistence.DTOS.BookDTO;
import app.api.Persistence.Entity.Book;
import app.api.Persistence.Repo.BookRepo;
import app.api.Persistence.mapper.BookMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.UnsupportedMediaTypeStatusException;

import java.io.IOException;

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



}
