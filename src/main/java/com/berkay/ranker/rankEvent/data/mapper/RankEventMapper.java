package com.berkay.ranker.rankEvent.data.mapper;

import com.berkay.ranker.rankEvent.data.dto.RankEventDTO;
import com.berkay.ranker.rankEvent.data.entity.RankEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RankEventMapper {

    @Mapping(source = "actorId", target = "actor.id")
    @Mapping(source = "subjectId", target = "subject.id")
    @Mapping(source = "rankingTypeId", target = "rankingType.id")
    @Mapping(source = "rank", target = "rankValue")
    RankEvent toRankEvent(RankEventDTO dto);

    @Mapping(source = "actor.id", target = "actorId")
    @Mapping(source = "subject.id", target = "subjectId")
    @Mapping(source = "rankingType.id", target = "rankingTypeId")
    @Mapping(source = "rankValue", target = "rank")
    RankEventDTO toRankEventDTO(RankEvent entity);
}
