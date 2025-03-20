package edu.escuelaing.arep.arep_taller_7;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import edu.escuelaing.arep.arep_taller_7.dto.PostDto;
import edu.escuelaing.arep.arep_taller_7.exception.PostException;
import edu.escuelaing.arep.arep_taller_7.model.PostEntity;
import edu.escuelaing.arep.arep_taller_7.repository.PostRepository;
import edu.escuelaing.arep.arep_taller_7.service.PostServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PostTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostServiceImpl postService;

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
}