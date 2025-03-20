package eci.arep.exception;

public class InvalidCredentialsException extends RuntimeException {

    public final static String INVALID_CREDENTIALS = "Invalid credentials";

    public InvalidCredentialsException() {
        super(INVALID_CREDENTIALS);
    }
}
