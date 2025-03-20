package eci.arep;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import eci.arep.service.PostService;
import eci.arep.controller.PostControllerImpl;
import eci.arep.dto.PostDto;
import eci.arep.exception.PostException;
import eci.arep.model.PostEntity;
import eci.arep.repository.PostRepository;
import eci.arep.service.PostServiceImpl;

public class PostTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostServiceImpl postService;

    @Mock
    private PostService postServiceMock;

    @InjectMocks
    private PostControllerImpl postController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldCreatePost() throws PostException {
        PostDto postDto = new PostDto();
        postDto.setContent("Taller7");
        PostEntity postEntity = new PostEntity(postDto);

        when(postRepository.save(any(PostEntity.class))).thenReturn(postEntity);
        PostEntity result = postService.createPost(postDto);

        assertNotNull(result);
        assertEquals("Taller7", result.getContent());
    }

    @Test
    public void shouldNotCreatePost() {
        PostDto postDto = new PostDto();
        postDto.setContent("");

        assertThrows(PostException.class, () -> postService.createPost(postDto));
    }

    @Test
    public void shouldGetPostById() throws PostException {
        PostEntity postEntity = new PostEntity();
        postEntity.setId(1L);
        postEntity.setContent("Taller7");

        when(postRepository.findById(1L)).thenReturn(Optional.of(postEntity));
        PostEntity result = postService.getPostById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Taller7", result.getContent());
    }

    @Test
    public void shouldNotGetPostById() {
        when(postRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(PostException.class, () -> postService.getPostById(1L));
    }

    @Test
    public void shouldUpdatePost() throws PostException {
        PostEntity postEntity = new PostEntity();
        postEntity.setId(1L);
        postEntity.setContent("Taller7");

        PostDto postDto = new PostDto();
        postDto.setContent("AREP Taller7");

        when(postRepository.findById(1L)).thenReturn(Optional.of(postEntity));
        when(postRepository.save(any(PostEntity.class))).thenReturn(postEntity);

        PostEntity result = postService.updatePost(1L, postDto);

        assertNotNull(result);
        assertEquals("AREP Taller7", result.getContent());
    }

    @Test
    public void shouldDeletePost() throws PostException {
        PostEntity postEntity = new PostEntity();
        postEntity.setId(1L);

        when(postRepository.findById(1L)).thenReturn(Optional.of(postEntity));

        postService.deletePost(1L);

        verify(postRepository, times(1)).delete(postEntity);
    }

    @Test
    public void shouldGetAllPosts() {
        PostEntity postEntity1 = new PostEntity();
        postEntity1.setId(1L);
        PostEntity postEntity2 = new PostEntity();
        postEntity2.setId(2L);

        when(postRepository.findAll()).thenReturn(Arrays.asList(postEntity1, postEntity2));

        List<PostEntity> result = postService.getAllPosts();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void shouldCreatePostEntity() throws PostException {
        PostDto postDto = new PostDto();
        postDto.setContent("Taller7");
        PostEntity postEntity = new PostEntity(postDto);

        when(postServiceMock.createPost(postDto)).thenReturn(postEntity);

        ResponseEntity<Object> response = postController.createPostEntity(postDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(postEntity, response.getBody());
    }

    @Test
    public void shouldNotCreatePostEntity() throws PostException {
        PostDto postDto = new PostDto();
        postDto.setContent("");

        when(postServiceMock.createPost(postDto)).thenThrow(new PostException("Invalid content length"));

        ResponseEntity<Object> response = postController.createPostEntity(postDto);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody() instanceof Map);
        assertEquals("Invalid content length", ((Map<?, ?>) response.getBody()).get("error"));
    }

    @Test
    public void shouldGetPostEntity() throws PostException {
        PostEntity postEntity = new PostEntity();
        postEntity.setId(1L);

        when(postServiceMock.getPostById(1L)).thenReturn(postEntity);

        ResponseEntity<Object> response = postController.getPostEntity(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(postEntity, response.getBody());
    }

    @Test
    public void shouldNotGetPostEntity() throws PostException {
        when(postServiceMock.getPostById(1L)).thenThrow(new PostException("Post not found"));

        ResponseEntity<Object> response = postController.getPostEntity(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody() instanceof Map);
        assertEquals("Post not found", ((Map<?, ?>) response.getBody()).get("error"));
    }

    @Test
    public void shouldUpdatePostEntity() throws PostException {
        PostDto postDto = new PostDto();
        postDto.setContent("AREP Taller7");
        PostEntity postEntity = new PostEntity();
        postEntity.setId(1L);

        when(postServiceMock.updatePost(1L, postDto)).thenReturn(postEntity);

        ResponseEntity<Object> response = postController.updatePostEntity(1L, postDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(postEntity, response.getBody());
    }

    @Test
    public void shouldDeletePostEntity() throws PostException {
        doNothing().when(postServiceMock).deletePost(1L);

        ResponseEntity<Object> response = postController.deletePostEntity(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(postServiceMock, times(1)).deletePost(1L);
    }

    @Test
    public void shouldGetAllPostEntities() {
        PostEntity postEntity = new PostEntity();
        postEntity.setId(1L);

        when(postServiceMock.getAllPosts()).thenReturn(Collections.singletonList(postEntity));

        ResponseEntity<Object> response = postController.getAllPostEntities();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof List);
        assertEquals(1, ((List<?>) response.getBody()).size());
    }
}