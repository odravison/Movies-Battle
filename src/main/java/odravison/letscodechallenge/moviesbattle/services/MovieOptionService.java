package odravison.letscodechallenge.moviesbattle.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import odravison.letscodechallenge.moviesbattle.entities.MovieOption;
import odravison.letscodechallenge.moviesbattle.repositories.MovieOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class MovieOptionService extends BaseEntityService<MovieOption, Long> {

    private MovieOptionRepository repository;

}
