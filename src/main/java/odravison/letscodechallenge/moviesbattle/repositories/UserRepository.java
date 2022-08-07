package odravison.letscodechallenge.moviesbattle.repositories;

import odravison.letscodechallenge.moviesbattle.entities.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {}
