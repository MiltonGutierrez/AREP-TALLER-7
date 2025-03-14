package edu.escuelaing.arep.arep_taller_7.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.escuelaing.arep.arep_taller_7.dto.UserDto;
import edu.escuelaing.arep.arep_taller_7.model.UserEntity;
import edu.escuelaing.arep.arep_taller_7.service.UserService;
import edu.escuelaing.arep.exception.UserException;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/user")
public class UserControllerImpl implements UserController {

    private static final Object ERROR_KEY = "error";
    private UserService userService;

    @Autowired
    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<Object> createUserEntity(UserDto userDto) {
        UserEntity user = userService.createUser(userDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> getUserEntity(Long id) throws UserException {
        try {
            UserEntity user = userService.getUserById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (UserException e) {
            return new ResponseEntity<>(Map.of(ERROR_KEY, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Object> getAllUserEntity() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> updateEntity(Long id, UserDto userDto) throws UserException {
        try {
            UserEntity user = userService.updateUser(id, userDto);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (UserException e) {
            return new ResponseEntity<>(Map.of(ERROR_KEY, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Object> deleteUserEntity(Long id) throws UserException {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (UserException e) {
            return new ResponseEntity<>(Map.of(ERROR_KEY, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
