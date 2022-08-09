package odravison.letscodechallenge.moviesbattle.repositories;

import odravison.letscodechallenge.moviesbattle.entities.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface BaseRepository<Entity extends BaseEntity<ID>, ID extends Serializable> extends JpaRepository<Entity, ID> {

    List<Entity> findAll();

}
