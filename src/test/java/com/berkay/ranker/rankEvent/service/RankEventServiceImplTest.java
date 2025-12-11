package com.berkay.ranker.rankEvent.service;

import com.berkay.ranker.rankEvent.data.dto.RankEventDTO;
import com.berkay.ranker.rankEvent.data.entity.RankEvent;
import com.berkay.ranker.rankEvent.data.entity.RankingType;
import com.berkay.ranker.rankEvent.data.mapper.RankEventMapper;
import com.berkay.ranker.rankEvent.data.repository.RankEventRepository;
import com.berkay.ranker.rankEvent.service.implementation.RankEventServiceImpl;
import com.berkay.ranker.user.data.entity.User;
import com.berkay.ranker.util.RankEventTestUtil;
import com.berkay.ranker.util.RankingTypeTestUtil;
import com.berkay.ranker.util.UserTestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RankEventServiceImplTest {
    @InjectMocks
    private RankEventServiceImpl service;
    @Mock
    private RankEventRepository repository;
    @Mock
    private RankEventMapper mapper;

    @Test
    void createRankEvent_ReturnsRankEventDTO(){
        Long actorId = 1L;
        Long subjectId = 2L;
        Long rankingTypeId = 3L;
        RankEventDTO rankEventDTO = RankEventTestUtil.getRankEventDTO(actorId, subjectId, rankingTypeId);
        User actor = UserTestUtil.getUser(actorId);
        User subject = UserTestUtil.getUser(subjectId);
        RankingType rankingType = RankingTypeTestUtil.getRankingType(rankingTypeId);
        RankEvent rankEvent = RankEventTestUtil.getRankEvent(actor, subject, rankingType);
        when(mapper.toRankEvent(rankEventDTO)).thenReturn(rankEvent);
        when(repository.save(rankEvent)).thenReturn(rankEvent);
        when(mapper.toRankEventDTO(rankEvent)).thenReturn(rankEventDTO);
        RankEventDTO response = service.createRankEvent(rankEventDTO);
        assertEquals(actorId, response.getActorId());
    }
}
