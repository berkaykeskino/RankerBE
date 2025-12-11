package com.berkay.ranker.util;

import com.berkay.ranker.rankEvent.data.dto.RankingTypeDTO;
import com.berkay.ranker.rankEvent.data.entity.RankingType;

public class RankingTypeTestUtil {

    public static RankingTypeDTO getRankingTypeDTO(Long id){
        RankingTypeDTO rankingTypeDTO = new RankingTypeDTO();
        rankingTypeDTO.setId(id);
        return rankingTypeDTO;
    }

    public static RankingType getRankingType(Long rankingTypeId){
        RankingType rankingType = new RankingType();
        rankingType.setId(rankingTypeId);
        return rankingType;
    }
}
