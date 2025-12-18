package com.berkay.ranker.rankEvent.service;

import com.berkay.ranker.rankEvent.data.dto.RankEventDTO;
import com.berkay.ranker.rankEvent.data.entity.RankEvent;
import com.berkay.ranker.rankEvent.data.mapper.RankEventMapper;
import com.berkay.ranker.rankEvent.data.repository.RankEventRepository;
import com.berkay.ranker.rankEvent.service.implementation.RankEventServiceImpl;
import com.berkay.ranker.util.RankEventTestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

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
        RankEvent rankEvent = RankEventTestUtil.getRankEvent(actorId, subjectId, rankingTypeId);
        when(mapper.toRankEvent(rankEventDTO)).thenReturn(rankEvent);
        when(repository.save(rankEvent)).thenReturn(rankEvent);
        when(mapper.toRankEventDTO(rankEvent)).thenReturn(rankEventDTO);
        RankEventDTO response = service.createRankEvent(rankEventDTO);
        assertEquals(actorId, response.getActorId());
    }

    @Test
    void getRankEventList_ReturnsRankEventListByActorId(){
        Long actorId = 1L;
        Long subjectId = 2L;
        Long rankingTypeId = 3L;
        RankEventDTO rankEventDTO = RankEventTestUtil.getRankEventDTO(actorId, subjectId, rankingTypeId);
        RankEvent rankEvent = RankEventTestUtil.getRankEvent(actorId, subjectId, rankingTypeId);
        when(repository.findByActor_Id(actorId)).thenReturn(List.of(rankEvent));
        when(mapper.toRankEventDTO(rankEvent)).thenReturn(rankEventDTO);
        List<RankEventDTO> response = service.getRankEventList(actorId, null);
        assertEquals(1, response.size());
        assertEquals(actorId, response.get(0).getActorId());
    }

    @Test
    void getRankEventList_ReturnsRankEventListBySubjectId(){
        Long actorId = 1L;
        Long subjectId = 2L;
        Long rankingTypeId = 3L;
        RankEventDTO rankEventDTO = RankEventTestUtil.getRankEventDTO(actorId, subjectId, rankingTypeId);
        RankEvent rankEvent = RankEventTestUtil.getRankEvent(actorId, subjectId, rankingTypeId);
        when(repository.findBySubject_Id(subjectId)).thenReturn(List.of(rankEvent));
        when(mapper.toRankEventDTO(rankEvent)).thenReturn(rankEventDTO);
        List<RankEventDTO> response = service.getRankEventList(null, subjectId);
        assertEquals(1, response.size());
        assertEquals(actorId, response.get(0).getActorId());
    }

    @Test
    void getRankEventList_ReturnsRankEventListByActorIdAndSubjectId(){
        Long actorId = 1L;
        Long subjectId = 2L;
        Long rankingTypeId = 3L;
        RankEventDTO rankEventDTO = RankEventTestUtil.getRankEventDTO(actorId, subjectId, rankingTypeId);
        RankEvent rankEvent = RankEventTestUtil.getRankEvent(actorId, subjectId, rankingTypeId);
        when(repository.findByActor_IdAndSubject_Id(actorId, subjectId)).thenReturn(List.of(rankEvent));
        when(mapper.toRankEventDTO(rankEvent)).thenReturn(rankEventDTO);
        List<RankEventDTO> response = service.getRankEventList(actorId, subjectId);
        assertEquals(1, response.size());
        assertEquals(actorId, response.get(0).getActorId());
    }

    @Test
    void getRankEventList_ReturnsEmptyList(){
        List<RankEventDTO> response = service.getRankEventList(null, null);
        assertEquals(0, response.size());
    }
}
