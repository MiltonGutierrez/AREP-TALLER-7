package edu.escuelaing.arep.arep_taller_7.service;

import edu.escuelaing.arep.arep_taller_7.dto.UserDto;
import edu.escuelaing.arep.arep_taller_7.model.UserEntity;
import edu.escuelaing.arep.arep_taller_7.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return (List<UserEntity>) userRepository.findAll();
    }

    @Override
    public UserEntity getUserById(Long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        if(user.isEmpty()){
            return null;
        }
        return user.get();
    }

    @Override
    public UserEntity createUser(UserDto userDto) {
        UserEntity userEntity = new UserEntity(userDto);
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity updateUser(Long id, UserDto userDto) {
        UserEntity user = getUserById(id);
        if(userDto.getUsername() != null){
            user.setUsername(userDto.getUsername());
        }
        if(userDto.getPassword() != null){
            user.setPassword(userDto.getPassword());
        }
        userRepository.save(user);
        return user;
    }

    @Override
    public void deleteUser(Long id) {
        UserEntity user = getUserById(id);
        if(user != null){
            userRepository.delete(user);
        }
    }
}
