package com.berkay.ranker.likeEvent.controller;

import com.berkay.ranker.likeEvent.controller.request.CreateLikeRequest;
import com.berkay.ranker.likeEvent.controller.response.CreateLikeResponse;
import com.berkay.ranker.likeEvent.service.LikeEventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/like")
@Tag(name = "LikeEventController", description = "CRUD operations for like event")
public class LikeEventController {
    private final LikeEventService service;

    @PostMapping
    @Operation(summary = "Creates the like event given in the request")
    public CreateLikeResponse createLike(@RequestBody CreateLikeRequest request){
        return new CreateLikeResponse(service.createLike(request.getLikeEventDTO()));
    }

}
