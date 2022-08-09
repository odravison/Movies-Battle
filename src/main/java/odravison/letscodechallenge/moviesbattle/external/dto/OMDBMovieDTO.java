package odravison.letscodechallenge.moviesbattle.external.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMDBMovieDTO {

    private String Title;
    private String Year;
    private String imdbID;
    private String Poster;
    private String imdbRating;
    private String imdbVotes;

}
