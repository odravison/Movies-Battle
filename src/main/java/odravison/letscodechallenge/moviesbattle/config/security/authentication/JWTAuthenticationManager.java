package odravison.letscodechallenge.moviesbattle.config.security.authentication;

import odravison.letscodechallenge.moviesbattle.config.dto.LoggedUserDTO;
import odravison.letscodechallenge.moviesbattle.config.security.AppContext;
import odravison.letscodechallenge.moviesbattle.dto.UserResponse;
import odravison.letscodechallenge.moviesbattle.services.UserService;
import odravison.letscodechallenge.moviesbattle.utils.CryptoUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class JWTAuthenticationManager implements AuthenticationManager {

    private UserService userService;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {

        UserResponse loggedUser = this.getUserService().login(auth.getName(), CryptoUtil.hash((String) auth.getCredentials()));

        if (loggedUser != null) {
            return new UsernamePasswordAuthenticationToken(toUserDTO(loggedUser), auth.getCredentials());
        }

        throw new BadCredentialsException("Invalid username and/or password.");
    }

    /**
     * Gets the User Service.
     */
    protected UserService getUserService() {

        if (this.userService == null) {
            this.userService = AppContext.getBean(UserService.class);
        }

        return this.userService;
    }

    /**
     * Converts the Logged User to DTO.
     *
     * @param user
     *            Logged User.
     * @return DTO.
     */
    private LoggedUserDTO toUserDTO(UserResponse user) {

        LoggedUserDTO userDTO = new LoggedUserDTO();

        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setLogin(user.getLogin());

        return userDTO;
    }
}
