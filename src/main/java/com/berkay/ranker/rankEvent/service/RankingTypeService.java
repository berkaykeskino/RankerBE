package com.berkay.ranker.rankEvent.service;

import com.berkay.ranker.rankEvent.data.dto.RankingTypeDTO;

public interface RankingTypeService {
    RankingTypeDTO createExternalRank(RankingTypeDTO request);
    RankingTypeDTO getRankingType(Long rankingTypeId);
}
