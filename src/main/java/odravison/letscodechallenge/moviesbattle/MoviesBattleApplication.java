package odravison.letscodechallenge.moviesbattle;

import odravison.letscodechallenge.moviesbattle.config.security.AppContext;
import odravison.letscodechallenge.moviesbattle.entities.User;
import odravison.letscodechallenge.moviesbattle.services.UserService;
import odravison.letscodechallenge.moviesbattle.utils.CryptoUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MoviesBattleApplication {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(MoviesBattleApplication.class, args);
        AppContext.loadApplicationContext(ctx);

        createDefaultUsers();

    }

    private static void createDefaultUsers() {
        UserService userService = AppContext.getBean(UserService.class);
        userService.insert(new User(null, "Odravison", "odra", CryptoUtil.hash("odra"), null));
        userService.insert(new User(null, "Maria Luisa", "malu", CryptoUtil.hash("malu"), null));
    }

}
