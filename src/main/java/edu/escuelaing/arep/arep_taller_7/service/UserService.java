package edu.escuelaing.arep.arep_taller_7.service;

import edu.escuelaing.arep.arep_taller_7.dto.UserDto;
import edu.escuelaing.arep.arep_taller_7.model.UserEntity;

import java.util.List;

public interface UserService {
    List<UserEntity> getAllUsers();
    UserEntity getUserById(Long id);
    UserEntity createUser(UserDto userDto);
    UserEntity updateUser(Long id, UserDto userDto);
    void deleteUser(Long id);
}
