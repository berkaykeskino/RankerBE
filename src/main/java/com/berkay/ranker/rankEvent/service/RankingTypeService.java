package com.berkay.ranker.rankEvent.service;

import com.berkay.ranker.rankEvent.data.dto.RankingTypeDTO;

import java.util.List;

public interface RankingTypeService {
    RankingTypeDTO createExternalRank(RankingTypeDTO request);
    RankingTypeDTO getRankingType(Long rankingTypeId);
    List<RankingTypeDTO> getAllRankingTypes();
}
