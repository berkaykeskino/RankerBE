package com.berkay.ranker.likeEvent.controller.response;

import com.berkay.ranker.likeEvent.data.dto.LikeEventDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateLikeResponse {
    private LikeEventDTO likeEventDTO;
}
