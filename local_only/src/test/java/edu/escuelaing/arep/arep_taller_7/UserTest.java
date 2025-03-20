package edu.escuelaing.arep.arep_taller_7;

import edu.escuelaing.arep.arep_taller_7.dto.UserDto;
import edu.escuelaing.arep.arep_taller_7.exception.UserException;
import edu.escuelaing.arep.arep_taller_7.model.UserEntity;
import edu.escuelaing.arep.arep_taller_7.repository.UserRepository;
import edu.escuelaing.arep.arep_taller_7.service.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    private UserDto mockDto;
    private UserEntity mockUser;
    private UserEntity user1;
    private UserEntity user2;

    @BeforeEach
    void setup(){
        mockDto = new UserDto("user", "secretPassword");
        mockUser = new UserEntity(mockDto);
        user1 = new UserEntity(2L, "user1", "password");
        user2 = new UserEntity(3L, "user2", "password");
    }

    @Test
    void UserService_getAllUsers(){
        List<UserEntity> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        when(userRepository.findAll()).thenReturn(users);

        List<UserEntity> usersRetrieved = userService.getAllUsers();

        Assertions.assertNotNull(usersRetrieved);
        Assertions.assertEquals(2, usersRetrieved.size());
    }

    @Test
    void UserService_getUserById() throws UserException {
        when(userRepository.findById(2L)).thenReturn(Optional.ofNullable(user1));

        UserEntity userEntity = userService.getUserById(2L);
        Assertions.assertNotNull(userEntity);
        Assertions.assertEquals(2L, userEntity.getId());
        Assertions.assertEquals("user1", userEntity.getUsername());
        Assertions.assertEquals("password", userEntity.getPassword());
    }

    @Test void UserService_getUserById_ThrowsNotFound(){
        when(userRepository.findById(2L)).thenReturn(Optional.empty());

        Assertions.assertThrows(UserException.class,
                () -> userService.getUserById(2L),
                "User not found");
    }

    @Test
    void UserService_getUserByUsername(){
        when(userRepository.findByUsername("user1")).thenReturn(Optional.ofNullable(user1));

        Optional<UserEntity> userEntity = userService.getUserByUsername("user1");
        Assertions.assertNotNull(userEntity);
        Assertions.assertEquals(2L, userEntity.get().getId());
        Assertions.assertEquals("user1", userEntity.get().getUsername());
        Assertions.assertEquals("password", userEntity.get().getPassword());
    }

    @Test
    void UserService_createUser() {
        UserDto userDto = new UserDto("user", "secretPassword");

        when(userRepository.save(Mockito.any(UserEntity.class))).thenReturn(mockUser);

        UserEntity userSaved = userService.createUser(userDto);

        Assertions.assertNotNull(userSaved);
        Assertions.assertEquals("user", userSaved.getUsername());
        Assertions.assertTrue(BCrypt.checkpw("secretPassword", userSaved.getPassword()));
    }

    @Test
    void UserService_updateUser() throws UserException {
        UserDto userDto = new UserDto("user", "newPassword");

        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(mockUser));
        when(userRepository.save(Mockito.any(UserEntity.class))).thenReturn(mockUser);

        UserEntity userUpdated = userService.updateUser(1L, userDto);
        Assertions.assertNotNull(userUpdated);
        Assertions.assertEquals("user", userUpdated.getUsername());
        Assertions.assertTrue(BCrypt.checkpw("newPassword", userUpdated.getPassword()));
    }

    @Test
    void UserService_deleteUser() throws UserException {
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));

        userService.deleteUser(1L);

        verify(userRepository, times(1)).delete(mockUser);
    }

    @Test
    void UserService_deleteUser_ThrowsNotFound() throws UserException {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(UserException.class,
                () -> userService.deleteUser(1L),
                "User not found");
    }
}
