package com.berkay.ranker.rankEvent.controller;

import com.berkay.ranker.rankEvent.controller.request.CreateRankEventRequest;
import com.berkay.ranker.rankEvent.controller.response.CreateRankEventResponse;
import com.berkay.ranker.rankEvent.data.dto.RankEventDTO;
import com.berkay.ranker.rankEvent.service.RankEventService;
import com.berkay.ranker.util.RankEventTestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RankEventControllerTest {
    @InjectMocks
    private RankEventController controller;
    @Mock
    private RankEventService service;

    @Test
    void createRankEvent_CallsService(){
        RankEventDTO rankEventDTO = RankEventTestUtil.getRankEventDTO(1L, 2L, 3L);
        CreateRankEventRequest request = new CreateRankEventRequest();
        request.setRankEventDTO(rankEventDTO);
        when(service.createRankEvent(rankEventDTO)).thenReturn(rankEventDTO);
        CreateRankEventResponse response = controller.createRankEvent(request);
        verify(service).createRankEvent(any());
        assertEquals(3L, response.getRankEventDTO().getRankingTypeId());
    }
}
