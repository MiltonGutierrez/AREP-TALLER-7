package edu.escuelaing.arep.arep_taller_7.exception;

public class PostException extends Exception {

    public static final String INVALID_CONTENT_LENGTH = "The content length must be between 1 and 140 characters";
    public static final String USER_NOT_FOUND = "The user with the given id was not found";
    public static final String POST_NOT_FOUND = "The post with the given id was not found";

    public PostException(String message) {
        super(message);
    }
    
}
