package odravison.letscodechallenge.moviesbattle.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import odravison.letscodechallenge.moviesbattle.entities.MatchQuiz;
import odravison.letscodechallenge.moviesbattle.repositories.MatchQuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class MatchQuizService extends BaseEntityService<MatchQuiz, Long> {

    private MatchQuizRepository repository;

}
