package odravison.letscodechallenge.moviesbattle.config.filters;

import odravison.letscodechallenge.moviesbattle.config.dto.LoggedUserDTO;
import odravison.letscodechallenge.moviesbattle.config.security.AppContext;
import odravison.letscodechallenge.moviesbattle.config.security.authentication.JWTAuthenticationManager;
import odravison.letscodechallenge.moviesbattle.config.security.authentication.TokenAuthenticationService;
import odravison.letscodechallenge.moviesbattle.utils.JSONUtils;
import odravison.letscodechallenge.moviesbattle.utils.SecurityUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTRefreshAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public JWTRefreshAuthenticationFilter(String url) {
        super(new AntPathRequestMatcher(url));
        this.setAuthenticationManager(new JWTAuthenticationManager());
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException {

        response = SecurityUtils.fillAccessControlHeader(response);

        return getTokenService().getRefreshAuthentication(request);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        LoggedUserDTO dto = getTokenService().addAuthentication((LoggedUserDTO) auth.getPrincipal());

        response.setHeader("Content-Type", "application/json");
        response.getWriter().write(JSONUtils.toJSon(dto));
    }

    private TokenAuthenticationService getTokenService() {
        return AppContext.getBean(TokenAuthenticationService.class);
    }

    private JWTAuthenticationManager getManager() {
        return (JWTAuthenticationManager) getAuthenticationManager();
    }
}
