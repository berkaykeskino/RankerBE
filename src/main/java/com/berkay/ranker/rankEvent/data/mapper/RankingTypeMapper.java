package com.berkay.ranker.rankEvent.data.mapper;

import com.berkay.ranker.rankEvent.data.dto.RankingTypeDTO;
import com.berkay.ranker.rankEvent.data.entity.RankingType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RankingTypeMapper {
    RankingType toRankingType(RankingTypeDTO rankingTypeDTO);

    RankingTypeDTO toRankingTypeDTO(RankingType rankingType);
}
