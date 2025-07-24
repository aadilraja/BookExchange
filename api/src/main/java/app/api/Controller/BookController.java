package app.api.Controller;

import app.api.Persistence.DTOS.BookDTO;
import app.api.Service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "*")
public class BookController {

    BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    ResponseEntity<BookDTO> AddBook(@Valid @RequestBody BookDTO bookDTO) {

     BookDTO bookdto =  bookService.persist(bookDTO);

     return ResponseEntity.status(HttpStatus.CREATED).body(bookdto);



    }

}
