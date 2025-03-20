package eci.arep.exception;


import jakarta.servlet.ServletException;

public class TokenExpiredException extends ServletException {

    public static final String INVALID_TOKEN = "Token expired or malformed";

    public TokenExpiredException() {
        super(INVALID_TOKEN);
    }

}