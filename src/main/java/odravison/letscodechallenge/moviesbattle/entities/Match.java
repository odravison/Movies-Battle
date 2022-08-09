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
@NoArgsConstructor
@SQLDelete(sql = DELETE_ME)
@Where(clause = BaseEntity.WHERE_DELETED_CLAUSE)
public class Match extends BaseEntity<Long> {
    public static final String DELETE_ME = "UPDATE match SET deleted = true WHERE id = ?";

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;


    @OneToMany(fetch = FetchType.EAGER)
    private List<MatchQuiz> matchQuizzes;

    @Column(name = "user_id")
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id",
            nullable = false, updatable = false, insertable = false)
    private User user;

    @Column(name = "match_ended")
    private Boolean matchEnded = Boolean.FALSE;

    public Match(Long userId) {
        this.userId = userId;
    }
}
