package odravison.letscodechallenge.moviesbattle.entities;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

import static odravison.letscodechallenge.moviesbattle.entities.User.DELETE_ME;


@Getter
@Setter
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = DELETE_ME)
@Where(clause = BaseEntity.WHERE_DELETED_CLAUSE)
@Entity(name = "user_account")
public class User extends BaseEntity<Long> {

    public static final String DELETE_ME = "UPDATE user_account SET deleted = true WHERE id = ?";

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column
    private String name;

    @Column
    private String login;

    @Column
    private String password;
}
