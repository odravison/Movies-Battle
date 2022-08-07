package odravison.letscodechallenge.moviesbattle.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import odravison.letscodechallenge.moviesbattle.entities.User;
import odravison.letscodechallenge.moviesbattle.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserService extends BaseEntityService<User, Long> {

    private UserRepository repository;

}
