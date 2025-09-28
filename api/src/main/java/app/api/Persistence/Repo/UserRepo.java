package app.api.Persistence.Repo;

import app.api.Persistence.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

   User findByEmail(String email);

}
