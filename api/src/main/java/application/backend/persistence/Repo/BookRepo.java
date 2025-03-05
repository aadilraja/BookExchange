package application.backend.persistence.Repo;

import application.backend.persistence.Model.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


@Repository
public interface BookRepo extends CrudRepository<Book,Long> {
    boolean existsByTitle(String title);

}
