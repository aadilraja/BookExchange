package application.backend.Service;

import application.backend.persistence.Repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BookService {

    BookRepo bookRepo;
    @Autowired
    public BookService(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }




}
