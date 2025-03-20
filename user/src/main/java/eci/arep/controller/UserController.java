package eci.arep.controller;

import org.springframework.http.ResponseEntity;

import eci.arep.dto.UserDto;
import eci.arep.exception.UserException;


public interface UserController {
    
    ResponseEntity<Object> createUserEntity(UserDto userDto);

    ResponseEntity<Object> getUserEntity(Long id) throws UserException;

    ResponseEntity<Object> getAllUserEntity();

    ResponseEntity<Object> updateEntity(Long id, UserDto userDto) throws UserException;

    ResponseEntity<Object> deleteUserEntity(Long id) throws UserException;

}
