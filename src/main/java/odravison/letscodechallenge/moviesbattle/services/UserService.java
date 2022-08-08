package odravison.letscodechallenge.moviesbattle.services;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import odravison.letscodechallenge.moviesbattle.dto.UserResponse;
import odravison.letscodechallenge.moviesbattle.entities.User;
import odravison.letscodechallenge.moviesbattle.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.ConstructorProperties;

@Service
@Getter
@NoArgsConstructor
public class UserService extends BaseEntityService<User, Long> {

    @Autowired
    private UserRepository repository;

    @Transactional
    public UserResponse login(String email, String encryptedPassword) {
        User user = this.repository.login(email, encryptedPassword);
        if (user == null) {
            return null;
        }
        return new UserResponse(user);
    }

    @Transactional
    public void updateLastLoginTime(Long id, Long lastLoginTime) {
        this.repository.updateLastLoginTimeByUserId(id, lastLoginTime);
    }

    @Transactional(readOnly = true)
    public UserResponse findByLogin(String login) {
        User userFound = this.repository.findByLogin(login);
        return new UserResponse(userFound);
    }
}
