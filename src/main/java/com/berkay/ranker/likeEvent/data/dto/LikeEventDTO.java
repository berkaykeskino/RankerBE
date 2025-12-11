package com.berkay.ranker.likeEvent.data.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LikeEventDTO {
    private Long id;

    private Long userId;

    private Long postId;
}
