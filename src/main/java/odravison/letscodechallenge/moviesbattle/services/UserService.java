package odravison.letscodechallenge.moviesbattle.services;

import lombok.Getter;
import lombok.NoArgsConstructor;
import odravison.letscodechallenge.moviesbattle.config.dto.LoggedUserDTO;
import odravison.letscodechallenge.moviesbattle.dto.UserResponse;
import odravison.letscodechallenge.moviesbattle.entities.User;
import odravison.letscodechallenge.moviesbattle.repositories.UserRepository;
import odravison.letscodechallenge.moviesbattle.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Getter
@NoArgsConstructor
public class UserService extends BaseEntityService<User, Long> {

    @Autowired
    private UserRepository repository;

    @Transactional
    public UserResponse login(String login, String encryptedPassword) {
        User user = this.repository.login(login, encryptedPassword);
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

    public LoggedUserDTO getLoggedUser() {
        return SecurityUtils.getCurrentUser();
    }

    public boolean isUserLoggedIn() {
        LoggedUserDTO loggedUser = getLoggedUser();
        return loggedUser != null;
    }
}
