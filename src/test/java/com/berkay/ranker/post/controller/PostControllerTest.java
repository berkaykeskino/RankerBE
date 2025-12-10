package com.berkay.ranker.post.controller;

import com.berkay.ranker.post.controller.request.CreatePostRequest;
import com.berkay.ranker.post.data.dto.PostDTO;
import com.berkay.ranker.post.service.PostService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostControllerTest {
    @InjectMocks
    private PostController controller;
    @Mock
    private PostService postService;

    @Test
    void createPost_CallsPostService(){
        PostDTO postDTO = new PostDTO();
        CreatePostRequest request = new CreatePostRequest();
        request.setPostDTO(postDTO);
        when(postService.createPost(any())).thenReturn(new PostDTO());
        controller.createPost(request);
        verify(postService).createPost(any());
    }
}
