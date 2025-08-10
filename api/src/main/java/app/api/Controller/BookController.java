package app.api.Controller;

import app.api.Persistence.DTOS.Responses.SuccessResponse;
import app.api.Persistence.DTOS.BookDTO;
import app.api.Service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/api/books")
@CrossOrigin("*")
public class BookController {

    BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }



    @PostMapping
    ResponseEntity<SuccessResponse<BookDTO>> createBook(@Valid @RequestPart BookDTO bookDTO,
                                                        @RequestPart MultipartFile coverImg) throws IOException
    {

         BookDTO bookdto =  bookService.persist(bookDTO,coverImg);

         return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<BookDTO>("New Book created Successfully",bookdto));

    }


}
