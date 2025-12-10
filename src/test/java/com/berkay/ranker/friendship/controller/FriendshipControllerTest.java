package com.berkay.ranker.friendship.controller;

import com.berkay.ranker.friendship.controller.request.SendFriendshipRequest;
import com.berkay.ranker.friendship.controller.response.SendFriendshipResponse;
import com.berkay.ranker.friendship.data.dto.FriendshipDTO;
import com.berkay.ranker.friendship.service.FriendshipService;
import com.berkay.ranker.util.FriendshipTestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FriendshipControllerTest {
    @InjectMocks
    private FriendshipController controller;
    @Mock
    private FriendshipService service;

    @Test
    void sendFriendship_CallsService(){
        SendFriendshipRequest request = FriendshipTestUtil.getSendFriendshipRequest(1L, 2L);
        FriendshipDTO friendshipDTO = FriendshipTestUtil.getFriendshipDTO(1L, 2L);
        when(service.sendFriendship(1L, 2L)).thenReturn(friendshipDTO);
        SendFriendshipResponse response = controller.sendFriendship(request);
        assertEquals(1L, response.getFriendshipDTO().getSenderId());
        assertEquals(2L, response.getFriendshipDTO().getReceiverId());
    }
}
