package eci.arep.dto;

import java.util.Date;

public record TokenDto (
        String token,
        Date expirationDate) {
}
