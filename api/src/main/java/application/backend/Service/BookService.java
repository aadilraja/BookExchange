package application.backend.Service;

import application.backend.persistence.Model.Book;
import application.backend.persistence.Repo.BookRepo;
import application.backend.utils.Pairs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.NoSuchElementException;


@Service
public class BookService {

    BookRepo bookRepo;
    FileService fileService;
    @Autowired
    public BookService(BookRepo bookRepo,FileService fileService) {

        this.bookRepo = bookRepo;
        this.fileService = fileService;
    }




//    public void addBook(Book book,MultipartFile coverImg) throws RuntimeException, IOException {
        public void addBook(Book book) throws RuntimeException, IOException {


            if(bookRepo.existsByTitle(book.getTitle()))
            {
                throw new RuntimeException("Book with following Title already exists");
            }
            try {
//                String filePath = fileService.saveCoverImg(coverImg);
//                book.setCoverImgPath(filePath);
                bookRepo.save(book);
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
                throw new IOException("Error in file Service layer:"+e.getMessage());
            }


    }

    public Book getBookById(Long bookId) throws IOException, NoSuchElementException {


            if (bookId == null || bookId <= 0) {
                throw new IOException("Invalid book id");
            }

            return bookRepo.findById(bookId).orElseThrow();


    }
    public Pairs<MediaType,InputStreamResource> getImageFile(Long bookId) throws IOException, NoSuchElementException {
        Book book = bookRepo.findById(bookId).orElseThrow();
        String filePath=book.getCoverImgPath();

        String fileType= Files.probeContentType(Paths.get(filePath));
        MediaType imgType = fileType != null ? MediaType.parseMediaType(fileType) : MediaType.IMAGE_JPEG;
        InputStreamResource coverImg= new  InputStreamResource(new FileInputStream(new File(filePath)));

        return new Pairs<>(imgType,coverImg) ;


    }





//    public ArrayList<Book> getBookForUserId(String user_id) throws Exception {
//        try {
//            return bookRepo.findAllBookOfUser(user_id);
//
//        }
//        catch(Exception e) {
//            throw new Exception("Error while retrieving the user book in service layer"+e);
//        }
//    }
}
