package eci.arep.controller;

import org.springframework.http.ResponseEntity;

import eci.arep.dto.PostDto;


public interface PostController {

    ResponseEntity<Object> createPostEntity(PostDto postDto);
    ResponseEntity<Object> getPostEntity(Long id);
    ResponseEntity<Object> updatePostEntity(Long id, PostDto postDto);
    ResponseEntity<Object> deletePostEntity(Long id);
    ResponseEntity<Object> getAllPostEntities();
    
}
