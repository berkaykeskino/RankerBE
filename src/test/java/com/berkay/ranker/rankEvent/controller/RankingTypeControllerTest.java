package com.berkay.ranker.rankEvent.controller;

import com.berkay.ranker.rankEvent.controller.request.CreateExternalRankRequest;
import com.berkay.ranker.rankEvent.data.dto.RankingTypeDTO;
import com.berkay.ranker.rankEvent.service.RankingTypeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RankingTypeControllerTest {
    @InjectMocks
    private RankingTypeController controller;
    @Mock
    private RankingTypeService service;

    @Test
    void createExternalRank_callsService(){
        when(service.createExternalRank(any())).thenReturn(new RankingTypeDTO());
        controller.createExternalRank(new CreateExternalRankRequest());
        verify(service).createExternalRank(any());
    }
}
