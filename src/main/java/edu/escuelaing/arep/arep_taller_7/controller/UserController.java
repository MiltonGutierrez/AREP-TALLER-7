package edu.escuelaing.arep.arep_taller_7.controller;

import org.springframework.http.ResponseEntity;

import edu.escuelaing.arep.arep_taller_7.dto.UserDto;
import edu.escuelaing.arep.arep_taller_7.exception.UserException;

public interface UserController {
    
    ResponseEntity<Object> createUserEntity(UserDto userDto);

    ResponseEntity<Object> getUserEntity(Long id) throws UserException;

    ResponseEntity<Object> getAllUserEntity();

    ResponseEntity<Object> updateEntity(Long id, UserDto userDto) throws UserException;

    ResponseEntity<Object> deleteUserEntity(Long id) throws UserException;

}
