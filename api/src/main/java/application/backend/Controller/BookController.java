package application.backend.Controller;

import application.backend.Service.BookService;
import application.backend.persistence.DTO.BookDTO;
import application.backend.persistence.Model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import application.backend.utils.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class BookController
{
   private final BookService bookService;
    private final Mapper mapper;
    @Autowired
    BookController(BookService bookService, Mapper mapper)
    {
        this.mapper = mapper;
        this.bookService = bookService;
    }


    @PostMapping("/add-book")
    public ResponseEntity<?> addBook(@RequestBody BookDTO bookdto)
    {
        try {
           Book book = mapper.toBook(bookdto);
            bookService.addBook(book);
            return new ResponseEntity<>("Your Book added successfully!", HttpStatus.OK);

        }
        catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/get-book/{user_id}")
    public ResponseEntity<List<BookDTO>> getBook(@PathVariable String user_id)
    {
        ArrayList<Book> books;
        try{
            books=bookService.getBookForUserId(user_id);
            if(books.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            List<BookDTO> booksDTO = books.stream()
                                          .map(mapper::toDTO)
                                          .toList();

            return new ResponseEntity<>(booksDTO,HttpStatus.OK);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }




}
