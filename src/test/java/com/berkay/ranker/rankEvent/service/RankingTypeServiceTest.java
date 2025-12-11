package com.berkay.ranker.rankEvent.service;

import com.berkay.ranker.rankEvent.data.dto.RankingTypeDTO;
import com.berkay.ranker.rankEvent.data.entity.RankingType;
import com.berkay.ranker.rankEvent.data.mapper.RankingTypeMapper;
import com.berkay.ranker.rankEvent.data.repository.RankingTypeRepository;
import com.berkay.ranker.rankEvent.service.implementation.RankingTypeServiceImpl;
import com.berkay.ranker.util.RankingTypeTestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RankingTypeServiceTest {
    @InjectMocks
    private RankingTypeServiceImpl service;
    @Mock
    private RankingTypeMapper mapper;
    @Mock
    private RankingTypeRepository repository;

    @Test
    void createExternalRank_ReturnsRankingTypeDTO(){
        RankingTypeDTO rankingTypeDTO = RankingTypeTestUtil.getRankingTypeDTO(1L);
        RankingType rankingType = RankingTypeTestUtil.getRankingType(1L);
        when(mapper.toRankingTypeDTO(rankingType)).thenReturn(rankingTypeDTO);
        when(repository.save(rankingType)).thenReturn(rankingType);
        when(mapper.toRankingType(rankingTypeDTO)).thenReturn(rankingType);
        RankingTypeDTO response = service.createExternalRank(rankingTypeDTO);
        assertEquals(1L, response.getId());
    }

}
