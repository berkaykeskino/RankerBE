package com.berkay.ranker.rankEvent.controller.request;

import com.berkay.ranker.rankEvent.data.dto.RankEventDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRankEventRequest {
    private RankEventDTO rankEventDTO;
}
