package application.backend.Controller;

import application.backend.Service.BookService;
import application.backend.persistence.DTO.BookDTO;
import application.backend.persistence.Model.Book;
import application.backend.utils.Pairs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import application.backend.utils.Mapper;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

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

    @PostMapping("/book")
    public ResponseEntity<?> addBook(@RequestPart BookDTO bookdto,
                                     @RequestPart MultipartFile coverImg)
    {
        try {
            if(bookdto == null||coverImg==null) {
                return ResponseEntity.badRequest().body("Book data is missing");
            }

            Book book = mapper.toBook(bookdto);

            bookService.addBook(book, coverImg);
            return ResponseEntity.ok("Book added successfully!");
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error adding book: " + e.getMessage());
        }
    }


    @GetMapping("/book")
    public ResponseEntity<BookDTO>getBook(@RequestParam Long bookId)
    {

        try
        {
            BookDTO bookdto = mapper.toDTO(bookService.getBookById(bookId));
            return new ResponseEntity<>(bookdto, HttpStatus.OK);
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
        catch (NoSuchElementException e)
        {
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }

    }

    @GetMapping("/book/img")
    public ResponseEntity<InputStreamResource> getCoverImage(@RequestParam Long bookId)
    {

        try {
            Pairs<MediaType, InputStreamResource> imgDate=bookService.getImageFile(bookId);


            return ResponseEntity.ok()
                        .contentType(imgDate.getKey())
                        .cacheControl(CacheControl.maxAge(1, TimeUnit.DAYS))
                        .body(imgDate.getValue());

        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
        catch (NoSuchElementException e)
        {
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }






















//    @GetMapping("/get-book/{user_id}")
//    public ResponseEntity<List<BookDTO>> getBook(@PathVariable String user_id)
//    {
//        ArrayList<Book> books;
//        try{
//            books=bookService.getBookForUserId(user_id);
//            if(books.isEmpty())
//            {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//            List<BookDTO> booksDTO = books.stream()
//                                          .map(mapper::toDTO)
//                                          .toList();
//
//            return new ResponseEntity<>(booksDTO,HttpStatus.OK);
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//    }




}
