package edu.escuelaing.arep.arep_taller_7.service;

import java.util.List;
import java.util.Optional;

import edu.escuelaing.arep.arep_taller_7.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.escuelaing.arep.arep_taller_7.dto.PostDto;
import edu.escuelaing.arep.arep_taller_7.exception.PostException;
import edu.escuelaing.arep.arep_taller_7.model.PostEntity;
import edu.escuelaing.arep.arep_taller_7.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService {
    
    private PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostEntity createPost(PostDto postDto) throws PostException {
        PostEntity postEntity = new PostEntity(postDto);
        if(postDto.getContent().isEmpty() || postDto.getContent().length() > 140) {
            throw new PostException(PostException.INVALID_CONTENT_LENGTH);
        }
        return postRepository.save(postEntity);
    }

    @Override
    public PostEntity getPostById(Long id) throws PostException {
        Optional<PostEntity> postEntity = postRepository.findById(id);
        if(postEntity.isEmpty()) {
            throw new PostException(PostException.POST_NOT_FOUND);
        }
        return postEntity.get();
    }

    @Override
    public PostEntity updatePost(Long id, PostDto postDto) throws PostException{
        PostEntity postEntity = getPostById(id);  
        if(!postDto.getContent().isEmpty()){
            postEntity.setContent(postDto.getContent());
        }
        return postRepository.save(postEntity);
    }

    @Override
    public void deletePost(Long id) throws PostException {
        PostEntity postEntity = getPostById(id);
        if(postEntity == null) {
            throw new PostException(PostException.POST_NOT_FOUND);
        }
        postRepository.delete(postEntity);
    }

    @Override
    public List<PostEntity> getAllPosts() {
        return (List<PostEntity>) postRepository.findAll();
    }
    
}
