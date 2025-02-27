package application.backend.persistence.Repo;

import application.backend.persistence.Model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepo extends CrudRepository<Book,Integer>
{

}
