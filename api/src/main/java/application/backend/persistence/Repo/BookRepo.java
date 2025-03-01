package application.backend.persistence.Repo;

import application.backend.persistence.Model.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


@Repository
public interface BookRepo extends CrudRepository<Book,Long> {
    @Query("select b from Book b where b.user_id= :user_id")
    ArrayList<Book> findAllBookOfUser(String user_id);
}
