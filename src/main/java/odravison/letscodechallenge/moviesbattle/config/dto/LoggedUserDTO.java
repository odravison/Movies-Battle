package odravison.letscodechallenge.moviesbattle.config.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoggedUserDTO {

    private Long id;
    private String name;
    private String login;
    private String token;
    private String refreshToken;

    public LoggedUserDTO(Long id, String login) {
        this.id = id;
        this.login = login;
    }
}
