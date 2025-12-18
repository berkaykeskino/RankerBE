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

    public static RankEvent getRankEvent(Long actorId, Long subjectId, Long rankingTypeId){
        RankEvent rankEvent = new RankEvent();
        User actor = UserTestUtil.getUser(actorId);
        User subject = UserTestUtil.getUser(subjectId);
        RankingType rankingType = RankingTypeTestUtil.getRankingType(rankingTypeId);
        rankEvent.setActor(actor);
        rankEvent.setSubject(subject);
        rankEvent.setRankingType(rankingType);
        return rankEvent;
    }

}
