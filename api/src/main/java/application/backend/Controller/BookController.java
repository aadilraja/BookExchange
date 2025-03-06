package application.backend.Controller;

import application.backend.Service.BookService;
import application.backend.Service.FileService;
import application.backend.persistence.DTO.BookDTO;
import application.backend.persistence.Model.Book;
import application.backend.utils.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import application.backend.utils.Mapper;
import org.springframework.web.multipart.MultipartFile;


import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
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
            if(bookdto == null)
                return ResponseEntity.badRequest().body("Book data is missing");
            if(coverImg == null)
                return ResponseEntity.badRequest().body("Cover image is missing");

            Book book = mapper.toBook(bookdto);

            if(bookService.findIfBookExist(book.getTitle()))
            {
                return ResponseEntity.badRequest().body("Book already exists");
            }


            String filePath = fileService.saveCoverImg(coverImg);


            bookService.addBook(book, filePath);

            return ResponseEntity.ok("Book added successfully!");
        }
        catch(Exception e) {
            e.printStackTrace();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error adding book: " + e.getMessage());
        }
    }
    @GetMapping("/book/search?bookId={bookId}")
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

    @GetMapping("/book/img")
    @ResponseBody
    public ResponseEntity<InputStreamResource> getImageDynamicType(@RequestParam Long bookId)
    {

        if (bookId == null || bookId <= 0) {
            return ResponseEntity.badRequest().build();
        }
        System.out.println("bookId = " + bookId);
        try {
            Book book=bookService.getBookById(bookId);
            if (book==null) {
                System.out.println("book not found");
                return ResponseEntity.notFound().build();
            }
            String filePath=book.getCoverImgPath();
            String fileType= Files.probeContentType(Paths.get(filePath));
            MediaType imgType = fileType != null ? MediaType.parseMediaType(fileType) : MediaType.IMAGE_JPEG;

            InputStream coverImg= new FileInputStream(new File(filePath));

                return ResponseEntity.ok()
                        .contentType(imgType)
                        .cacheControl(CacheControl.maxAge(1, TimeUnit.DAYS))
                        .body(new InputStreamResource(coverImg));

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
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
