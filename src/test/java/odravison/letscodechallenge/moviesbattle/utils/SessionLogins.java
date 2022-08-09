package odravison.letscodechallenge.moviesbattle.utils;

import odravison.letscodechallenge.moviesbattle.config.dto.LoggedUserDTO;
import odravison.letscodechallenge.moviesbattle.config.security.authentication.AccountCredentials;
import odravison.letscodechallenge.moviesbattle.config.security.authentication.JWTAuthenticationManager;
import odravison.letscodechallenge.moviesbattle.config.security.authentication.TokenAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collections;

public class SessionLogins {

    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;

    public void loginWithOdra() {
        AccountCredentials credentials = new AccountCredentials();
        credentials.setLogin("odra");
        credentials.setPassword("odra");
        doLogin(credentials);
    }

    public void loginWithMalu() {
        AccountCredentials credentials = new AccountCredentials();
        credentials.setLogin("malu");
        credentials.setPassword("malu");
        doLogin(credentials);
    }

    private void doLogin(AccountCredentials credentials) {
        JWTAuthenticationManager jwtAuthenticationManager = new JWTAuthenticationManager();
        Authentication auth = jwtAuthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credentials.getLogin(),
                credentials.getPassword(), Collections.emptyList()));

        LoggedUserDTO dto = (LoggedUserDTO) auth.getPrincipal();
        this.tokenAuthenticationService.addAuthentication(dto);

        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    public void logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }

}
