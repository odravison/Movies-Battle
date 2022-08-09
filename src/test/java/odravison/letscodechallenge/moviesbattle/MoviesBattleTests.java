package odravison.letscodechallenge.moviesbattle;

import com.google.gson.GsonBuilder;
import odravison.letscodechallenge.moviesbattle.config.security.AppContext;
import odravison.letscodechallenge.moviesbattle.dto.MatchInfoDTO;
import odravison.letscodechallenge.moviesbattle.external.OMDBApi;
import odravison.letscodechallenge.moviesbattle.external.OMDBApiService;
import odravison.letscodechallenge.moviesbattle.external.dto.OMDBMovieDTO;
import odravison.letscodechallenge.moviesbattle.services.MatchService;
import odravison.letscodechallenge.moviesbattle.services.UserService;
import odravison.letscodechallenge.moviesbattle.utils.SecurityUtils;
import odravison.letscodechallenge.moviesbattle.utils.SessionLogins;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

import static odravison.letscodechallenge.moviesbattle.utils.MessagesUtils.NO_USER_LOGGED_IN;
import static odravison.letscodechallenge.moviesbattle.utils.MessagesUtils.USER_HAS_MATCH_CREATED;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MoviesBattleTests extends SessionLogins {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private OMDBApiService omdbApiService;

    @Autowired
    private UserService userService;

    @Autowired
    private MatchService matchService;

    @BeforeAll
    public void setupBeforeAll() {
        AppContext.loadApplicationContext(context);
        MoviesBattleApplication.createDefaultUsers();
    }

    @AfterEach
    public void tearDown() {
        logout();
        matchService.deleteAll();
    }


    @Test
    public void createMatchWhenNotLoggedInThenReturnException() {
        Exception exceptionThrown = assertThrows(Exception.class, () -> {
            this.matchService.createMatch();
        });
        assertEquals(NO_USER_LOGGED_IN, exceptionThrown.getMessage());
    }

    @Test
    public void createMatchWhenLoggedInThenReturnMatchInfo() throws Exception {
        loginWithOdra();
        MatchInfoDTO response = this.matchService.createMatch();
        assertNotNull(response, "Match info must not be null");
        assertNotNull(response.getId(), "Match must have an ID");
    }
    @Test
    public void createMatchWhenThereIsOneMatchCreatedThenReturnException() throws Exception {
        loginWithOdra();
        this.matchService.createMatch();
        Exception exceptionThrown = assertThrows(Exception.class, () -> {
            this.matchService.createMatch();
        });

        assertEquals(USER_HAS_MATCH_CREATED, exceptionThrown.getMessage());

    }

    @Test
    public void getTwoDistinctMoviesRandomly() {

        OMDBMovieDTO movie1 = omdbApiService.getValidNextMovie();
        OMDBMovieDTO movie2 = omdbApiService.getValidNextMovie();

        assertNotEquals(movie1.getImdbID(), movie2.getImdbID());

    }



}
