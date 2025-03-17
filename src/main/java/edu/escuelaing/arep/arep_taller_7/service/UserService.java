package edu.escuelaing.arep.arep_taller_7.service;

import edu.escuelaing.arep.arep_taller_7.dto.UserDto;
import edu.escuelaing.arep.arep_taller_7.exception.UserException;
import edu.escuelaing.arep.arep_taller_7.model.UserEntity;

import java.util.List;

public interface UserService {
    List<UserEntity> getAllUsers();
    UserEntity getUserById(Long id) throws UserException;
    UserEntity createUser(UserDto userDto);
    UserEntity updateUser(Long id, UserDto userDto) throws UserException;
    void deleteUser(Long id) throws UserException;
}
