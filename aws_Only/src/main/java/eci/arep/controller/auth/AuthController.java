package eci.arep.controller.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import eci.arep.dto.LoginDto;
import eci.arep.dto.TokenDto;
import eci.arep.exception.InvalidCredentialsException;
import eci.arep.model.UserEntity;
import eci.arep.security.JwtUtil;
import eci.arep.service.UserService;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private static final Object ERROR_KEY = "error";

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/public")
    public ResponseEntity<?> publicEndpoint() {
        return ResponseEntity.ok("El endpoint funciona.");
    }

    @PostMapping
    public ResponseEntity<Object> login(@RequestBody LoginDto loginDto) {
        Optional<UserEntity> optionalUser = userService.getUserByUsername(loginDto.getUsername());
        if(optionalUser.isPresent()){
            UserEntity userEntity = optionalUser.get();
            if(BCrypt.checkpw(loginDto.getPassword(), userEntity.getPassword())){
                TokenDto tokenDto = jwtUtil.generateToken(loginDto.getUsername());
                return ResponseEntity.ok(tokenDto);
            }
            else{
                return new ResponseEntity<>(Map.of(ERROR_KEY, InvalidCredentialsException.INVALID_CREDENTIALS), HttpStatus.UNAUTHORIZED);
            }
        }
        else{
            return new ResponseEntity<>(Map.of(ERROR_KEY, InvalidCredentialsException.INVALID_CREDENTIALS), HttpStatus.UNAUTHORIZED);
        }
    }
}