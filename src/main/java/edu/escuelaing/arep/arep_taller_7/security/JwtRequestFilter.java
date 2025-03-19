package edu.escuelaing.arep.arep_taller_7.security;

import edu.escuelaing.arep.arep_taller_7.controller.auth.TokenAuthentication;
import edu.escuelaing.arep.arep_taller_7.exception.TokenExpiredException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtRequestFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");
        try {
            if(authorizationHeader != null && authorizationHeader.startsWith("Bearer")){
                String jwt = authorizationHeader.split(" ")[1];
                Claims claims = jwtUtil.extractAndVerifyClaims(jwt);
                String username = claims.getSubject();
                if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                    TokenAuthentication tokenAuthentication = new TokenAuthentication(jwt, username);
                    SecurityContextHolder.getContext().setAuthentication(tokenAuthentication);
                }
            }
        } catch (ExpiredJwtException e) {
            throw new TokenExpiredException();
        }
        filterChain.doFilter(request, response);
    }

}