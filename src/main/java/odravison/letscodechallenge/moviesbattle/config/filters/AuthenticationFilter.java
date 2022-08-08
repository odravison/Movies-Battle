package odravison.letscodechallenge.moviesbattle.config.filters;

import io.jsonwebtoken.JwtException;
import odravison.letscodechallenge.moviesbattle.config.dto.ErrorResponse;
import odravison.letscodechallenge.moviesbattle.config.security.AppContext;
import odravison.letscodechallenge.moviesbattle.config.security.authentication.TokenAuthenticationService;
import odravison.letscodechallenge.moviesbattle.config.security.authentication.exception.MultipleTokenGeneratedException;
import odravison.letscodechallenge.moviesbattle.utils.JSONUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        try {

            /* Checks the Authorization */
            Authentication auth = getTokenService().getAuthentication(request);

            SecurityContextHolder.getContext().setAuthentication(auth);

            chain.doFilter(request, response);
        } catch (JwtException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } catch (MultipleTokenGeneratedException mte) {
            ErrorResponse errorResponse = new ErrorResponse(
                    System.currentTimeMillis(),
                    HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                    mte.getMessage(),
                    null);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getOutputStream().write(JSONUtils.toJSon(errorResponse).getBytes());
        }
    }

    private TokenAuthenticationService getTokenService() {
        return AppContext.getBean(TokenAuthenticationService.class);
    }
}
