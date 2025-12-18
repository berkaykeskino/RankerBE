package com.berkay.ranker.rankEvent.controller.response;

import com.berkay.ranker.rankEvent.data.dto.RankEventDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class RankEventListResponse {
    private List<RankEventDTO> rankEventDTOList;
}
