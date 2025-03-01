package application.backend.Service;

import application.backend.persistence.Model.Book;
import application.backend.persistence.Repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class BookService {

    BookRepo bookRepo;
    @Autowired
    public BookService(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }


    public void addBook(Book book) throws Exception {
        try {
            bookRepo.save(book);
        }
        catch(Exception e) {
            throw new Exception("Error while adding a book in service layer"+e);
        }
    }

    public ArrayList<Book> getBookForUserId(String user_id) throws Exception {
        try {
            return bookRepo.findAllBookOfUser(user_id);

        }
        catch(Exception e) {
            throw new Exception("Error while retrieving the user book in service layer"+e);
        }
    }
}
