package odravison.letscodechallenge.moviesbattle.external;

import odravison.letscodechallenge.moviesbattle.external.dto.OMDBMovieDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OMDBApi {

    @GET("/?type=movie")
    Call<OMDBMovieDTO> getRandomMovie(@Query("apikey") String apiKey, @Query("i") String IMDBId);

}
