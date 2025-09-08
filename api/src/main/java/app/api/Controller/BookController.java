package app.api.Controller;

import app.api.Persistence.DTOS.Responses.SuccessResponse;
import app.api.Persistence.DTOS.BookDTO;
import app.api.Service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api/books")
public class BookController {

    BookService bookService;
    MessageSource msg;

    @Autowired
    public BookController(BookService bookService,MessageSource msg) {
        this.bookService = bookService;
        this.msg = msg;
    }



    @PostMapping
    ResponseEntity<SuccessResponse<BookDTO>> createBook(@Valid @RequestPart BookDTO bookDTO,
                                                        @RequestPart MultipartFile coverImg) throws IOException
    {

         BookDTO bookdto =  bookService.persist(bookDTO,coverImg);
         String successMessage="success.book.created";
         String responseMsg=msg.getMessage(successMessage,null, LocaleContextHolder.getLocale());

         return ResponseEntity.status(HttpStatus.CREATED)
                 .body(new SuccessResponse<>(responseMsg, bookdto));

    }

    @GetMapping
    ResponseEntity<SuccessResponse<List<BookDTO>>> getAllBooks()
    {
        List<BookDTO> books=bookService.getAllBooks();
        String successMessage="message.book.retrieved";
        String responseMsg=msg.getMessage(successMessage,null, LocaleContextHolder.getLocale());

        return ResponseEntity.ok(new SuccessResponse<>(responseMsg, books));

    }
    @GetMapping("/{id}")
    ResponseEntity<SuccessResponse<BookDTO>>getBookById(@PathVariable("id") Long id)
    {
        BookDTO bookDTO=bookService.getBookById(id);
        String successMessage="retrieved the book successfully";
        String responseMsg=msg.getMessage(successMessage,null, LocaleContextHolder.getLocale());
        return ResponseEntity.ok(new SuccessResponse<>(responseMsg, bookDTO));

    }


}
