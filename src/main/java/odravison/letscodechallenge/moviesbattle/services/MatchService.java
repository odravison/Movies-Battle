package odravison.letscodechallenge.moviesbattle.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import odravison.letscodechallenge.moviesbattle.entities.Match;
import odravison.letscodechallenge.moviesbattle.repositories.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class MatchService extends BaseEntityService<Match, Long> {

    private MatchRepository repository;

}
