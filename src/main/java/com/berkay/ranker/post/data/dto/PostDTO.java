package com.berkay.ranker.post.data.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDTO {
    private Long id;
    private String title;
    private String content;
    private Long likeCount;
    private Long userId;
}
