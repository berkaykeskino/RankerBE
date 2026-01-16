package com.berkay.ranker.rankEvent.controller;


import com.berkay.ranker.rankEvent.controller.request.CreateExternalRankRequest;
import com.berkay.ranker.rankEvent.controller.response.CreateExternalRankResponse;
import com.berkay.ranker.rankEvent.controller.response.RankingTypeResponse;
import com.berkay.ranker.rankEvent.data.dto.RankingTypeDTO;
import com.berkay.ranker.rankEvent.service.RankingTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{rankingTypeId}")
    @Operation(summary = "Get ranking type with the given id")
    public RankingTypeResponse getRankingType(@PathVariable("rankingTypeId") Long rankingTypeId){
        return new RankingTypeResponse(service.getRankingType(rankingTypeId));
    }

    @GetMapping("/all")
    @Operation(summary = "Get all ranking types")
    public List<RankingTypeDTO> getAllRankingTypes() {
        return service.getAllRankingTypes();
    }
}
