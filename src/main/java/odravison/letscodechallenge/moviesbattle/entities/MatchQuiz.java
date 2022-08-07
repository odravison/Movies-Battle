package odravison.letscodechallenge.moviesbattle.entities;


import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

import static odravison.letscodechallenge.moviesbattle.entities.MatchQuiz.DELETE_ME;

@Entity(name = "match_quiz")
@Getter
@Setter
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = DELETE_ME)
@Where(clause = BaseEntity.WHERE_DELETED_CLAUSE)
public class MatchQuiz extends BaseEntity<Long> {

    public static final String DELETE_ME = "UPDATE match_quiz SET deleted = true WHERE id = ?";

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @OneToMany(fetch = FetchType.EAGER)
    private List<MovieOption> options;

    @Column(name = "movie_option_right_answer", updatable = false, nullable = false)
    private Long movieOptionRightAnswer;

    @Column(name = "movie_option_user_answer")
    private Long movieOptionUserAnswer;

    @ManyToOne(optional = false)
    @JoinColumn(name = "match_id", referencedColumnName = "id", nullable = false, updatable = false)
    private Match match;
}
