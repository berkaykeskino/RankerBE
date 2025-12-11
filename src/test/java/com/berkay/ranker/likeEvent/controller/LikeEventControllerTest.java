package com.berkay.ranker.likeEvent.controller;

import com.berkay.ranker.likeEvent.controller.request.CreateLikeRequest;
import com.berkay.ranker.likeEvent.controller.response.CreateLikeResponse;
import com.berkay.ranker.likeEvent.data.dto.LikeEventDTO;
import com.berkay.ranker.likeEvent.service.LikeEventService;
import com.berkay.ranker.util.LikeEventTestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LikeEventControllerTest {
    @InjectMocks
    private LikeEventController controller;
    @Mock
    private LikeEventService service;

    @Test
    void createLike_callsService(){
        LikeEventDTO likeEventDTO = LikeEventTestUtil.getLikeEventDTO(1L, 1L);
        CreateLikeRequest createLikeRequest = new CreateLikeRequest(likeEventDTO);
        when(service.createLike(likeEventDTO)).thenReturn(likeEventDTO);
        CreateLikeResponse response = controller.createLike(createLikeRequest);
        assertEquals(1L, response.getLikeEventDTO().getUserId());
    }
}
