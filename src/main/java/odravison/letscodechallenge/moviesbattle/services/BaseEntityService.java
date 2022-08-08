package odravison.letscodechallenge.moviesbattle.services;

import odravison.letscodechallenge.moviesbattle.entities.BaseEntity;
import odravison.letscodechallenge.moviesbattle.repositories.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

public abstract class BaseEntityService<Entity extends BaseEntity<IdType>, IdType extends Serializable> {

    protected abstract BaseRepository<Entity, IdType> getRepository();

    @Transactional(readOnly = true)
    public Entity findOne(IdType id) {
        return getRepository().findById(id).get();
    }

    @Transactional(readOnly = true)
    public List<Entity> findAll(){
        return getRepository().findAll();
    }

    @Transactional(readOnly = true)
    public Page<Entity> findAllPageable(Pageable pageable){
        return getRepository().findAll(pageable);
    }

    @Transactional
    public Entity insert(Entity entity) {
        return getRepository().save(entity);
    }

    @Transactional
    public void update(IdType id, Entity entity) {
        getRepository().save(entity);
    }

    @Transactional
    public void delete(IdType id) {
        getRepository().deleteById(id);
    }

}
