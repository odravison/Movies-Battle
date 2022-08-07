package odravison.letscodechallenge.moviesbattle.repositories;

import odravison.letscodechallenge.moviesbattle.entities.MatchQuiz;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchQuizRepository extends BaseRepository<MatchQuiz, Long> {}
