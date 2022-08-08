package odravison.letscodechallenge.moviesbattle;

import odravison.letscodechallenge.moviesbattle.config.security.AppContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MoviesBattleApplication {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(MoviesBattleApplication.class, args);
        AppContext.loadApplicationContext(ctx);
    }

}
