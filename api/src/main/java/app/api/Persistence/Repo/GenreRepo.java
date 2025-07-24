package app.api.Persistence.Repo;

import app.api.Persistence.Entity.Genre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepo extends CrudRepository<Genre, Long> {
    Optional<Genre> findByName(String name);
}