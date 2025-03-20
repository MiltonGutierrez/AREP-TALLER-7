package edu.escuelaing.arep.arep_taller_7.exception;

public class InvalidCredentialsException extends RuntimeException {

    public static final String INVALID_CREDENTIALS = "Invalid credentials";

    public InvalidCredentialsException() {
        super(INVALID_CREDENTIALS);
    }
}
