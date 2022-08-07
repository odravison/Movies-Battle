package odravison.letscodechallenge.moviesbattle.entities;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;

import static odravison.letscodechallenge.moviesbattle.entities.MovieOption.DELETE_ME;

@Entity(name = "movie_option")
@Getter
@Setter
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = DELETE_ME)
@Where(clause = BaseEntity.WHERE_DELETED_CLAUSE)
public class MovieOption extends BaseEntity<Long> {

    public static final String DELETE_ME = "UPDATE movie_option SET deleted = true WHERE id = ?";

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false, updatable = false)
    private String movieId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "match_quiz_id", referencedColumnName = "id", nullable = false, updatable = false)
    private MatchQuiz matchQuiz;

    @Column(scale = 1)
    private BigDecimal scorePoints;

}
