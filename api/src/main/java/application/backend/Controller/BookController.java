package application.backend.Controller;

import application.backend.Service.BookService;
import application.backend.Service.FileService;
import application.backend.persistence.DTO.BookDTO;
import application.backend.persistence.Model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import application.backend.utils.Mapper;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class BookController
{
   private final BookService bookService;
   private final Mapper mapper;
   private final FileService fileService;

    @Autowired
    BookController(BookService bookService, Mapper mapper, FileService fileService)
    {
        this.mapper = mapper;
        this.bookService = bookService;
        this.fileService = fileService;
    }


    @PostMapping("/book")
    public ResponseEntity<?> addBook(@RequestPart BookDTO bookdto,
                                     @RequestPart MultipartFile coverImg)
    {
        try {
            if(bookdto==null || coverImg==null)
                return new  ResponseEntity<>("no data recieved",HttpStatus.BAD_REQUEST);

            String filePath=fileService.saveCoverImg(coverImg);
            Book book = mapper.toBook(bookdto);
            bookService.addBook(book,filePath);
            return new ResponseEntity<>("Your Book added successfully!", HttpStatus.OK);

        }
        catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/book/{bookId}")
    public ResponseEntity<BookDTO>getBook(@PathVariable Long bookId)
    {
        try
        {
            if(bookService.getBookById(bookId)==null)
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            BookDTO bookdto = mapper.toDTO(bookService.getBookById(bookId));
            return new ResponseEntity<>(bookdto, HttpStatus.OK);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
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
