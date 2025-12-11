package com.berkay.ranker.rankEvent.controller.response;

import com.berkay.ranker.rankEvent.data.dto.RankEventDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateRankEventResponse {
    private RankEventDTO rankEventDTO;
}
