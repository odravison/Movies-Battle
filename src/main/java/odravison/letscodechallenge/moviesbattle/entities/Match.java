package odravison.letscodechallenge.moviesbattle.entities;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

import java.util.List;

import static odravison.letscodechallenge.moviesbattle.entities.Match.DELETE_ME;

@Entity(name = "match")
@Getter
@Setter
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = DELETE_ME)
@Where(clause = BaseEntity.WHERE_DELETED_CLAUSE)
public class Match extends BaseEntity<Long> {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    public static final String DELETE_ME = "UPDATE match SET deleted = true WHERE id = ?";

//    @Column(name = "match_quiz_id")
//    private Long matchQuizId;

    @OneToMany(fetch = FetchType.EAGER)
    private List<MatchQuiz> matchQuizzes;
}
