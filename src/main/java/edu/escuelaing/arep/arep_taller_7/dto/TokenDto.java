package edu.escuelaing.arep.arep_taller_7.dto;

import java.util.Date;

public record TokenDto (
        String token,
        Date expirationDate) {
}
