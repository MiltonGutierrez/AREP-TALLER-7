package eci.arep.exception;

public class UserException extends Exception {

    public static final String USER_NOT_FOUND = "User not found";

    public UserException(String message){
        super(message);
    }
}