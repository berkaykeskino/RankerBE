package com.berkay.ranker.rankEvent.service;

import com.berkay.ranker.rankEvent.data.dto.RankEventDTO;

import java.util.List;

public interface RankEventService {
    RankEventDTO createRankEvent(RankEventDTO request);

    List<RankEventDTO> getRankEventList(Long actorId, Long subjectId);
}
