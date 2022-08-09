package odravison.letscodechallenge.moviesbattle.repositories;

import odravison.letscodechallenge.moviesbattle.entities.Match;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends BaseRepository<Match, Long> {
    Match findByUserIdAndMatchEndedIsFalse(Long id);

    /*@Query(value = "select 1 from match where user_id = ?1 and match_ended = false and deleted = false;"
            , nativeQuery = true)*/
    Boolean existsMatchByUserIdAndMatchEndedFalse(Long id);

}
