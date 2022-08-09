package odravison.letscodechallenge.moviesbattle.external;

import odravison.letscodechallenge.moviesbattle.external.dto.OMDBMovieDTO;
import org.cornutum.regexpgen.RandomGen;
import org.cornutum.regexpgen.RegExpGen;
import org.cornutum.regexpgen.js.Parser;
import org.cornutum.regexpgen.random.RandomBoundsGen;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

@Service
public class OMDBApiService {

    private static final String IMDB_ID_REGEX_PATTERN = "^tt\\d\\d\\d\\d\\d\\d\\d";

    @Value("${app.OMDB-api-key}")
    private String OMDB_API_KEY;

    private Retrofit retrofit;
    private OMDBApi api;

    private RandomGen random;

    private RegExpGen generator;

    public OMDBApiService() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://www.omdbapi.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(OMDBApi.class);

        random = new RandomBoundsGen();
        generator = Parser.parseRegExpExact(IMDB_ID_REGEX_PATTERN);
    }

    public String getRandomIMDBId() {
        return generator.generate(random);
    }

    public OMDBMovieDTO getValidNextMovie() {
        try {
            String randomIMDBId = getRandomIMDBId();
            OMDBMovieDTO response;
            do {
                response = this.api.getRandomMovie(OMDB_API_KEY, randomIMDBId).execute().body();
            } while(response.getImdbRating() == "N/A");
            return response;
        } catch (IOException e) {
            System.out.println("Error while fetching movie, returning null. Try again;");
            return null;
        }
    }
}
