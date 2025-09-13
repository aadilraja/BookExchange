package app.api.Service;

import app.api.Filter.JwtAuthFilter;
import app.api.Persistence.DTOS.BookDTO;
import app.api.Persistence.Entity.Book;
import app.api.Persistence.Entity.User;
import app.api.Persistence.Repo.BookRepo;
import app.api.Service.mapper.BookMapper;
import jakarta.annotation.Nullable;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.UnsupportedMediaTypeStatusException;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@Service
public class BookService {

    @Value("${app.base.url}")
    private String baseUrl;

   private final BookMapper bookMapper;
    private final BookRepo bookRepo;
    private final FileService fileService;
    private final JwtAuthFilter jwtAuthFilter;
    private final UserService userService;

    public BookService(BookMapper bookMapper,BookRepo bookRepo,
                       FileService fileService,JwtAuthFilter jwtAuthFilter,
                       UserService userService) {
        this.bookMapper = bookMapper;
        this.bookRepo = bookRepo;
        this.fileService = fileService;
        this.jwtAuthFilter = jwtAuthFilter;
        this.userService = userService;
    }



    //Adding Book to DB
    public BookDTO persist(BookDTO bookDTO, MultipartFile imgFile, HttpServletRequest request) throws
                                                                         IOException,
                                                                         IllegalArgumentException,
                                                                         UnsupportedMediaTypeStatusException {
        if (bookDTO == null||imgFile==null) {
            throw new IllegalArgumentException("Request cannot be null");
        }



        Long userId = jwtAuthFilter.extractUserId(request);

        User user = userService.findUserById(userId);
        if (user == null) {
            throw new EntityNotFoundException("User not found");
        }


        Book book = bookMapper.toEntity(bookDTO);
        if (book == null) {
            throw new IllegalArgumentException("Invalid book  - mapping failed");
        }
        String imgPath=fileService.uploadImage(imgFile);
        String imgUrl = baseUrl + "/api/books/images/" +fileService.extractFileName(imgPath);

        book.setImageUrl(imgUrl);
        book.setImagePath(imgPath);
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

    public BookDTO updateBookById(Long id, BookDTO bookDTO,MultipartFile imgFile) throws IOException {

        Book existingBook = bookRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id));

        if(bookDTO==null)
            throw new IllegalArgumentException("Invalid Argument- bookDto is null");

        if (imgFile != null && !imgFile.isEmpty()) {
            if (existingBook.getImagePath() != null)
                fileService.DeleteFileAtPath(existingBook.getImagePath());

            String imgPath = fileService.uploadImage(imgFile);
            existingBook.setImagePath(imgPath);

            String imgUrl = baseUrl + "/api/books/images/" + fileService.extractFileName(imgPath);
            existingBook.setImageUrl(imgUrl);
        }
        bookMapper.updateEntityFromDto(existingBook,bookDTO);

        Book updatedBook=bookRepo.save(existingBook);
        return bookMapper.toDto(updatedBook);

    }

    public void deleteBookById(Long id) throws IOException {
        Book book=bookRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id));

        if (book.getImagePath() != null)
            fileService.DeleteFileAtPath(book.getImagePath());


        System.out.println("deleted book is"+ book);

        bookRepo.delete(book);

    }

}
