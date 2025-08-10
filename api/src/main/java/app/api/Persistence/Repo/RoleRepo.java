package app.api.Persistence.Repo;

import app.api.Persistence.Entity.ERole;
import app.api.Persistence.Entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepo extends CrudRepository<Role, Integer> {

    Optional<Role> findByName(ERole eRole);
}

