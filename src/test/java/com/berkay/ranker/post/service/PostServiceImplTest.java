package com.berkay.ranker.post.service;

import com.berkay.ranker.common.exceptionHandling.customExceptions.ResourceNotFoundException;
import com.berkay.ranker.post.data.dto.PostDTO;
import com.berkay.ranker.post.data.entity.Post;
import com.berkay.ranker.post.data.mapper.PostMapper;
import com.berkay.ranker.post.data.repository.PostRepository;
import com.berkay.ranker.post.service.implementation.PostServiceImpl;
import com.berkay.ranker.user.data.entity.User;
import com.berkay.ranker.user.data.repository.UserRepository;
import com.berkay.ranker.util.PostTestUtil;
import com.berkay.ranker.util.UserTestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {
    @InjectMocks
    private PostServiceImpl service;
    @Mock
    private PostRepository repository;
    @Mock
    private PostMapper mapper;
    @Mock
    private UserRepository userRepository;

    @Test
    void createPost_ThrowsResourceNotFoundException(){
        PostDTO postDTO = PostTestUtil.getPostDTO(1L, 1L);
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> service.createPost(postDTO));
        assertEquals("user.not.found", exception.getMessage());
    }

    @Test
    void createPost_ReturnsPostDTO(){
        PostDTO postDTO = PostTestUtil.getPostDTO(1L, 1L);
        User user = UserTestUtil.getUser(1L);
        Post post = PostTestUtil.getPost(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(mapper.toPost(postDTO)).thenReturn(post);
        when(repository.save(post)).thenReturn(post);
        when(mapper.toPostDTO(post)).thenReturn(postDTO);
        PostDTO response = service.createPost(postDTO);
        assertEquals(1L, response.getId());
    }

}
