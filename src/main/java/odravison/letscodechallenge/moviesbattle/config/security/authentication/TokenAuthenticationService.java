package odravison.letscodechallenge.moviesbattle.config.security.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import odravison.letscodechallenge.moviesbattle.config.dto.LoggedUserDTO;
import odravison.letscodechallenge.moviesbattle.config.dto.TokenDTO;
import odravison.letscodechallenge.moviesbattle.config.security.AppContext;
import odravison.letscodechallenge.moviesbattle.config.security.authentication.exception.MultipleTokenGeneratedException;
import odravison.letscodechallenge.moviesbattle.dto.UserResponse;
import odravison.letscodechallenge.moviesbattle.services.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

import static java.util.Collections.emptyList;

@Service
public class TokenAuthenticationService {

    private UserService userService;

    public static final String HEADER = "Authorization";

    protected static final String CLAIM_TIME_ISSUED = "time-issued";

    @Value("${app.token.secretkey}")
    private String SECRET_KEY;

    /**
     * Token Expiration.
     */
    @Value("${app.token.expiration}")
    private long EXPIRATION_TOKEN;

    /**
     * Refresh Token Expiration.
     */
    @Value("${app.refreshtoken.expiration}")
    private long EXPIRATION_REFRESH_TOKEN;

    /**
     * Adds the authentication in the response.
     */
    public LoggedUserDTO addAuthentication(LoggedUserDTO dto) {

        Long lastLoginTime = System.currentTimeMillis();

        String jwt = this.generateToken(lastLoginTime, dto, EXPIRATION_TOKEN);
        String refresh = this.generateToken(lastLoginTime, dto, EXPIRATION_REFRESH_TOKEN);

        dto.setToken(jwt);
        dto.setRefreshToken(refresh);

        this.getUserService().updateLastLoginTime(dto.getId(), lastLoginTime);

        return dto;
    }

    /**
     * Gets the authentication.
     */
    public Authentication getAuthentication(HttpServletRequest request) throws MultipleTokenGeneratedException, IOException {

        String token = request.getHeader(HEADER);

        if (token != null) {
            Claims claims = Jwts.parser().setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token).getBody();

            if (claims != null) {
                return this.processTokenClaims(claims);
            }
        }

        return null;
    }

    /**
     * Generate Token.
     */
    private String generateToken(Long lastLoginTime, LoggedUserDTO dto, long expirationTokenTime) {

        return Jwts.builder().setId(String.valueOf(dto.getId())).setSubject(dto.getLogin())
                .claim(CLAIM_TIME_ISSUED, lastLoginTime)
                .setExpiration(new Date(lastLoginTime + expirationTokenTime))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
    }

    /**
     * Gets the refresh authentication.
     */
    public Authentication getRefreshAuthentication(HttpServletRequest request) throws MultipleTokenGeneratedException {

        String refreshToken;
        try {
            refreshToken = new ObjectMapper().readValue(request.getInputStream(), TokenDTO.class).getToken();
        } catch (IOException e) {
            return null;
        }

        if (refreshToken != null) {
            Claims claims = Jwts.parser().setSigningKey(SECRET_KEY)
                    .parseClaimsJws(refreshToken).getBody();

            if (claims != null) {
                return this.processTokenClaims(claims);
            }

        }

        return null;
    }

    private Authentication processTokenClaims(Claims claims) throws MultipleTokenGeneratedException {
        Long lastLoginTime = Long.valueOf(claims.get(CLAIM_TIME_ISSUED).toString());

        /*
         * Check if it's the last token generated, if not must re-login;
         */
        UserResponse userResponse = this.getUserService().findByLogin(claims.getSubject());
        this.checkIfIsLastTokenGenerated(userResponse, lastLoginTime);

        LoggedUserDTO userDTO = new LoggedUserDTO(Long.valueOf(claims.getId()),
                claims.getSubject());
        return new UsernamePasswordAuthenticationToken(userDTO, null, emptyList());
    }

    private void checkIfIsLastTokenGenerated(UserResponse userResponse, Long lastLoginTimeClaim) throws MultipleTokenGeneratedException {
        if (userResponse != null && userResponse.getLastLoginTime() != null) {
            if (userResponse.getLastLoginTime() > lastLoginTimeClaim) {
                throw new MultipleTokenGeneratedException("Session expired / Invalid token");
            }
        }
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

}
