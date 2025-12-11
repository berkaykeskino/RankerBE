package com.berkay.ranker.rankEvent.controller.request;

import com.berkay.ranker.rankEvent.data.dto.RankingTypeDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateExternalRankRequest {
    private RankingTypeDTO rankingTypeDTO;
}
