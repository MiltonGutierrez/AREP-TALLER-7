package edu.escuelaing.arep.arep_taller_7.repository;

import edu.escuelaing.arep.arep_taller_7.model.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
}
