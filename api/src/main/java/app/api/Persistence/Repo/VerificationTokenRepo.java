package app.api.Persistence.Repo;

import app.api.Persistence.Entity.VerificationToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerificationTokenRepo extends CrudRepository<VerificationToken, Long> {
    Optional<VerificationToken> findByToken(String token);
}
