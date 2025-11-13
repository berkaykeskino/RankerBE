package com.berkay.ranker.post.controller.request;

import com.berkay.ranker.post.data.dto.PostDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePostRequest {
    private PostDTO postDTO;
}
