package com.berkay.ranker.rankEvent.controller;

import com.berkay.ranker.rankEvent.controller.request.CreateRankEventRequest;
import com.berkay.ranker.rankEvent.controller.response.CreateRankEventResponse;
import com.berkay.ranker.rankEvent.controller.response.RankEventListResponse;
import com.berkay.ranker.rankEvent.service.RankEventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rank-event")
@Tag(name = "RankEventController", description = "CRUD operations for Rank Events")
public class RankEventController {
    private final RankEventService service;

    @PostMapping
    @Operation(summary = "Creates rank event")
    public CreateRankEventResponse createRankEvent(@RequestBody CreateRankEventRequest request){
        return new CreateRankEventResponse(service.createRankEvent(request.getRankEventDTO()));
    }

    @GetMapping
    @Operation(summary = "Returns rank events for the given parameters")
    public RankEventListResponse getRankEventList(@RequestParam(value = "actorId", required = false) Long actorId,
                                                  @RequestParam(value = "subjectId", required = false) Long subjectId){
        return new RankEventListResponse(service.getRankEventList(actorId, subjectId));
    }
}
