package eci.arep.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import eci.arep.model.PostEntity;

@Repository
public interface PostRepository extends CrudRepository<PostEntity, Long> {
    
}
