package eci.arep.service;

import java.util.List;

import eci.arep.dto.PostDto;
import eci.arep.exception.PostException;
import eci.arep.model.PostEntity;


public interface PostService {
    
    PostEntity createPost(PostDto postDto) throws PostException;
    PostEntity getPostById(Long id) throws PostException;
    PostEntity updatePost(Long id, PostDto postDto) throws PostException;
    void deletePost(Long id)  throws PostException;
    List<PostEntity> getAllPosts();

}
