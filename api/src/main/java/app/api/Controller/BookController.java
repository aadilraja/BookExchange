package app.api.Controller;

import app.api.Persistence.DTOS.Responses.SuccessResponse;
import app.api.Persistence.DTOS.BookDTO;
import app.api.Service.BookService;
import jakarta.servlet.http.HttpServletRequest;
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

  private final BookService bookService;
 private final  MessageSource msg;

    @Autowired
    public BookController(BookService bookService, MessageSource msg) {
        this.bookService = bookService;
        this.msg = msg;
    }


//TODO: have to extract user Id from jwt token in the httpOnly cookie
    @PostMapping
    ResponseEntity<SuccessResponse<BookDTO>> createBook(@Valid @RequestPart BookDTO bookDTO,
                                                        @RequestPart MultipartFile coverImg, HttpServletRequest request)
                                                        throws IOException
    {

         BookDTO bookdto =  bookService.persist(bookDTO,coverImg,request);
         String responseMsg=createResponseMsg("success.book.created");


         return ResponseEntity.status(HttpStatus.CREATED)
                 .body(new SuccessResponse<>(responseMsg, bookdto));

    }

    @GetMapping
    ResponseEntity<SuccessResponse<List<BookDTO>>> getAllBooks()
    {
        List<BookDTO> books=bookService.getAllBooks();
        String responseMsg=createResponseMsg("message.book.retrieved");

        return ResponseEntity.ok(new SuccessResponse<>(responseMsg, books));

    }
    @GetMapping("/{id}")
    ResponseEntity<SuccessResponse<BookDTO>>getBookById(@PathVariable("id") Long id)
    {
        BookDTO bookDTO=bookService.getBookById(id);
        String responseMsg=createResponseMsg("message.book.retrieved.id");

        return ResponseEntity.ok(new SuccessResponse<>(responseMsg, bookDTO));

    }
    @PutMapping("/{id}")
    ResponseEntity<SuccessResponse<BookDTO>>updateBookById(@PathVariable("id") Long id,
                                                           @Valid @RequestPart BookDTO bookDTO,
                                                           @RequestPart(required = false) MultipartFile coverImg)
                                                           throws IOException

    {
        BookDTO bookDto=bookService.updateBookById(id,bookDTO,coverImg);
        String responseMsg=createResponseMsg("message.book.updated");

        return ResponseEntity.ok(new SuccessResponse<>(responseMsg, bookDto));
    }
    @DeleteMapping("/{id}")
    ResponseEntity<SuccessResponse<?>>deleteBookById(@PathVariable("id") Long id) throws IOException {
        bookService.deleteBookById(id);
        String responseMsg=createResponseMsg("message.book.deleted");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new SuccessResponse<>(responseMsg));

    }


    private String createResponseMsg(String message)
    {
        return msg.getMessage(message,null,LocaleContextHolder.getLocale());

    }


}
