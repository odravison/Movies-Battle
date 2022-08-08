package odravison.letscodechallenge.moviesbattle.config.security;

import odravison.letscodechallenge.moviesbattle.config.filters.AuthenticationFilter;
import odravison.letscodechallenge.moviesbattle.config.filters.CORSOptionsFilter;
import odravison.letscodechallenge.moviesbattle.config.filters.JWTRefreshAuthenticationFilter;
import odravison.letscodechallenge.moviesbattle.config.filters.LoginFilter;
import odravison.letscodechallenge.moviesbattle.config.security.authentication.JWTAuthenticationManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeHttpRequests(
                        // Login resource
                        auth ->
                                auth.antMatchers(HttpMethod.POST, "/login").permitAll()
                        .antMatchers(HttpMethod.POST, "/refresh").permitAll()
                        .anyRequest().authenticated()
                        .and()
                        .addFilterBefore(new CORSOptionsFilter(), UsernamePasswordAuthenticationFilter.class)
                        .addFilterBefore(new LoginFilter("/login"), UsernamePasswordAuthenticationFilter.class)
                        .addFilterBefore(new JWTRefreshAuthenticationFilter("/refresh"), UsernamePasswordAuthenticationFilter.class)
                        .addFilterBefore(new AuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                )
                .authenticationManager(new JWTAuthenticationManager());

        return http.build();
    }
}
