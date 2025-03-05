package application.backend.Service;

import application.backend.persistence.Model.Book;
import application.backend.persistence.Repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class BookService {

    BookRepo bookRepo;
    @Autowired
    public BookService(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }


    public void addBook(Book book,String filePath) throws Exception {

        try {
            book.setCoverImgPath(filePath);
            bookRepo.save(book);
        }
        catch(Exception e) {
            throw new Exception("Error while adding a book in service layer"+e);
        }
    }
    public Book getBookById(Long bookId) throws Exception {
        try
        {

              return bookRepo.findById(bookId).orElse(null);

        }
        catch(Exception e)
        {
            throw new Exception("Error while getting a book by id in service layer"+e);
        }

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
