package app.api.Persistence.Repo;

import app.api.Persistence.Entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {

   User findByEmail(String email);

}
