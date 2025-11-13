package com.berkay.ranker.post.controller.response;

import com.berkay.ranker.post.data.dto.PostDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostResponse {
    private PostDTO postDTO;
}
