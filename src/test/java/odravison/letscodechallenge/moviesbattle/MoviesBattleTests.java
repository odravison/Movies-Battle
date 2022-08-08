package odravison.letscodechallenge.moviesbattle;

import odravison.letscodechallenge.moviesbattle.services.MatchService;
import odravison.letscodechallenge.moviesbattle.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MoviesBattleTests {

    @Autowired
    private UserService userService;

    @Autowired
    private MatchService matchService;

     @Test
    public void createMatchOnlyThereIsNoMatchRunning() throws Exception {

     }

}
