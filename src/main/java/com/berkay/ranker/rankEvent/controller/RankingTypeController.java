package com.berkay.ranker.rankEvent.controller;


import com.berkay.ranker.rankEvent.controller.request.CreateExternalRankRequest;
import com.berkay.ranker.rankEvent.controller.response.CreateExternalRankResponse;
import com.berkay.ranker.rankEvent.service.RankingTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ranking-type")
@Tag(name = "RankingTypeController", description = "CRUD operations for Ranking Types")
public class RankingTypeController {
    private final RankingTypeService service;

    @PostMapping
    @Operation(summary = "Add external ranker")
    public CreateExternalRankResponse createExternalRank(@RequestBody CreateExternalRankRequest request){
        return new CreateExternalRankResponse(service.createExternalRank(request.getRankingTypeDTO()));
    }

}
