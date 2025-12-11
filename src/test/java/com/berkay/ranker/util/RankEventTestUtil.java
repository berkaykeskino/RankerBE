package com.berkay.ranker.util;

import com.berkay.ranker.rankEvent.data.dto.RankEventDTO;
import com.berkay.ranker.rankEvent.data.entity.RankEvent;
import com.berkay.ranker.rankEvent.data.entity.RankingType;
import com.berkay.ranker.user.data.entity.User;

public class RankEventTestUtil {
    public static RankEventDTO getRankEventDTO(Long actorId, Long subjectId, Long rankingTypeId){
        RankEventDTO rankEventDTO = new RankEventDTO();
        rankEventDTO.setActorId(actorId);
        rankEventDTO.setSubjectId(subjectId);
        rankEventDTO.setRankingTypeId(rankingTypeId);
        return rankEventDTO;
    }

    public static RankEvent getRankEvent(User actor, User subject, RankingType rankingType){
        RankEvent rankEvent = new RankEvent();
        rankEvent.setActor(actor);
        rankEvent.setSubject(subject);
        rankEvent.setRankingType(rankingType);
        return rankEvent;
    }

}
