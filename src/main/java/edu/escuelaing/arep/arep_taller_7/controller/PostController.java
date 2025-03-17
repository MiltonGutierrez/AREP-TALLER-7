package edu.escuelaing.arep.arep_taller_7.controller;

import org.springframework.http.ResponseEntity;

import edu.escuelaing.arep.arep_taller_7.dto.PostDto;
import edu.escuelaing.arep.arep_taller_7.exception.PostException;

public interface PostController {

    ResponseEntity<Object> createPostEntity(PostDto postDto);
    ResponseEntity<Object> getPostEntity(Long id);
    ResponseEntity<Object> updatePostEntity(Long id, PostDto postDto);
    ResponseEntity<Object> deletePostEntity(Long id);
    ResponseEntity<Object> getAllPostEntities();
    
}
