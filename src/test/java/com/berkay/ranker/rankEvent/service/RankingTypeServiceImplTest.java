package com.berkay.ranker.rankEvent.service;

import com.berkay.ranker.common.exceptionHandling.customExceptions.ResourceNotFoundException;
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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RankingTypeServiceImplTest {
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

    @Test
    void getRankingType_ReturnsRankingTypeDTO(){
        Long rankingTypeId = 1L;
        RankingTypeDTO rankingTypeDTO = RankingTypeTestUtil.getRankingTypeDTO(rankingTypeId);
        RankingType rankingType = RankingTypeTestUtil.getRankingType(rankingTypeId);
        when(repository.findById(rankingTypeId)).thenReturn(Optional.of(rankingType));
        when(mapper.toRankingTypeDTO(rankingType)).thenReturn(rankingTypeDTO);
        RankingTypeDTO response = service.getRankingType(rankingTypeId);
        assertEquals(rankingTypeId, response.getId());
    }

    @Test
    void getRankingType_ThrowsException(){
        when(repository.findById(any())).thenReturn(Optional.empty());
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> service.getRankingType(1L));
        assertEquals("ranking-type.not.found", exception.getMessage());
    }

}
