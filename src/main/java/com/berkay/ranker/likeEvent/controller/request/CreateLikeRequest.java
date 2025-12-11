package com.berkay.ranker.likeEvent.controller.request;

import com.berkay.ranker.likeEvent.data.dto.LikeEventDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateLikeRequest {
    private LikeEventDTO likeEventDTO;
}
