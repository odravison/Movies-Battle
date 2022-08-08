package odravison.letscodechallenge.moviesbattle.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import odravison.letscodechallenge.moviesbattle.entities.User;

@Getter
@Setter
@AllArgsConstructor
public class UserResponse {
    private Long id;

    private String name;

    private String login;

    private Long lastLoginTime;

    public UserResponse(User user) {
        if (user != null){
            this.id = user.getId();
            this.name = user.getName();
            this.login = user.getLogin();
            this.lastLoginTime = user.getLastLoginTime();
        }
    }

}
