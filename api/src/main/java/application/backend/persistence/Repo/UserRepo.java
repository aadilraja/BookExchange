package application.backend.persistence.Repo;

import application.backend.persistence.Model.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<UserInfo,String> {
}
