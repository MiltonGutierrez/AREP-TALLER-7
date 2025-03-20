package eci.arep.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import eci.arep.model.UserEntity;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}
