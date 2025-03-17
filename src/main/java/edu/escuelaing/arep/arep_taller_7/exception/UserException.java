
package edu.escuelaing.arep.arep_taller_7.exception;

public class UserException extends Exception {

    public static final String USER_NOT_FOUND = "User not found";

    public UserException(String message){
        super(message);
    }
}