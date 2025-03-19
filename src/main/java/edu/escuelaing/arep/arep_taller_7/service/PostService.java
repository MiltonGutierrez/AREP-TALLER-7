package edu.escuelaing.arep.arep_taller_7.service;

import java.util.List;
import java.util.Optional;

import edu.escuelaing.arep.arep_taller_7.dto.PostDto;
import edu.escuelaing.arep.arep_taller_7.exception.PostException;
import edu.escuelaing.arep.arep_taller_7.model.PostEntity;
import edu.escuelaing.arep.arep_taller_7.model.UserEntity;

public interface PostService {
    
    PostEntity createPost(PostDto postDto) throws PostException;
    PostEntity getPostById(Long id) throws PostException;
    PostEntity updatePost(Long id, PostDto postDto) throws PostException;
    void deletePost(Long id)  throws PostException;
    List<PostEntity> getAllPosts();

}
