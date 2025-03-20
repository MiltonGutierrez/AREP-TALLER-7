package eci.arep.service;

import java.util.List;
import java.util.Optional;

import eci.arep.dto.UserDto;
import eci.arep.exception.UserException;
import eci.arep.model.UserEntity;

public interface UserService {
    List<UserEntity> getAllUsers();
    UserEntity getUserById(Long id) throws UserException;
    Optional<UserEntity> getUserByUsername(String username);
    UserEntity createUser(UserDto userDto);
    UserEntity updateUser(Long id, UserDto userDto) throws UserException;
    void deleteUser(Long id) throws UserException;
}
