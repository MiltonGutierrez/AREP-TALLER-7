package eci.arep.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eci.arep.dto.PostDto;
import eci.arep.exception.PostException;
import eci.arep.model.PostEntity;
import eci.arep.service.PostService;


@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class PostControllerImpl implements PostController {

    private PostService postService;
    private static final Object ERROR_KEY = "error";

    @Autowired
    public PostControllerImpl(PostService postService) {
        this.postService = postService;
    }

    @Override
    @PostMapping
    public ResponseEntity<Object> createPostEntity(@RequestBody PostDto postDto) {
        try {
            PostEntity postEntity = postService.createPost(postDto);
            return new ResponseEntity<>(postEntity, HttpStatus.CREATED);
        } catch (PostException e) {
            return new ResponseEntity<>(Map.of(ERROR_KEY, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<Object> getPostEntity(@PathVariable Long id){
        try {
            PostEntity postEntity = postService.getPostById(id);
            return new ResponseEntity<>(postEntity, HttpStatus.OK);
        } catch (PostException e) {
            return new ResponseEntity<>(Map.of(ERROR_KEY, e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @PutMapping("{id}")
    public ResponseEntity<Object> updatePostEntity(@PathVariable Long id, @RequestBody PostDto postDto){
        try {
            PostEntity postEntity = postService.updatePost(id, postDto);
            return new ResponseEntity<>(postEntity, HttpStatus.OK);
        } catch (PostException e) {
            return new ResponseEntity<>(Map.of(ERROR_KEY, e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @DeleteMapping("{id}")
    public ResponseEntity<Object> deletePostEntity(@PathVariable Long id){
        try {
            postService.deletePost(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (PostException e) {
            return new ResponseEntity<>(Map.of(ERROR_KEY, e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @GetMapping
    public ResponseEntity<Object> getAllPostEntities() {
        return new ResponseEntity<>(postService.getAllPosts(), HttpStatus.OK);
    }
    
}
