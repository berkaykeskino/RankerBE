package com.berkay.ranker.friendship.controller;

import com.berkay.ranker.friendship.controller.request.SendFriendshipRequest;
import com.berkay.ranker.friendship.controller.response.SendFriendshipResponse;
import com.berkay.ranker.friendship.data.dto.FriendshipDTO;
import com.berkay.ranker.friendship.service.FriendshipService;
import com.berkay.ranker.security.model.CustomUserDetails;
import com.berkay.ranker.user.data.entity.User;
import com.berkay.ranker.util.FriendshipTestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FriendshipControllerTest {

    @InjectMocks
    private FriendshipController controller;

    @Mock
    private FriendshipService service;

    @Test
    void sendFriendship_CallsService(){
        Long senderId = 1L;
        Long receiverId = 2L;
        SendFriendshipRequest request = new SendFriendshipRequest();
        request.setReceiverId(receiverId);
        CustomUserDetails mockUserDetails = mock(CustomUserDetails.class);
        User mockUser = new User();
        mockUser.setId(senderId);
        when(mockUserDetails.getUser()).thenReturn(mockUser);
        FriendshipDTO friendshipDTO = FriendshipTestUtil.getFriendshipDTO(senderId, receiverId);
        when(service.sendFriendship(senderId, receiverId)).thenReturn(friendshipDTO);
        SendFriendshipResponse response = controller.sendFriendship(request, mockUserDetails);
        assertEquals(senderId, response.getFriendshipDTO().getSenderId());
        assertEquals(receiverId, response.getFriendshipDTO().getReceiverId());
    }
}