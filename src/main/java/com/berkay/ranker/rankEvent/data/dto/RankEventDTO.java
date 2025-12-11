package com.berkay.ranker.rankEvent.data.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RankEventDTO {
    private Long id;
    private Long actorId;
    private Long subjectId;
    private Long rank;
    private Long totalCandidates;
    private Long rankingTypeId;
}
