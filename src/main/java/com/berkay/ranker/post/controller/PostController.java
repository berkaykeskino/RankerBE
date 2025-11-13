package com.berkay.ranker.post.controller;

import com.berkay.ranker.post.controller.request.CreatePostRequest;
import com.berkay.ranker.post.controller.response.PostResponse;
import com.berkay.ranker.post.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
@Tag(name = "PostController", description = "CRUD methods for post")
public class PostController {

    private final PostService postService;

    @PostMapping
    @Operation(summary = "Create post entity")
    public PostResponse createPost(@RequestBody CreatePostRequest createPostRequest){
        return new PostResponse(postService.createPost(createPostRequest.getPostDTO()));
    }
}
