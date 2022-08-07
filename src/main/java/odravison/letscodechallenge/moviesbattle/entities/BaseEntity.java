package odravison.letscodechallenge.moviesbattle.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;


@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity<IdType extends Serializable> {

    public static final String WHERE_DELETED_CLAUSE = "deleted = false";

    @Column
    private boolean deleted;

}
