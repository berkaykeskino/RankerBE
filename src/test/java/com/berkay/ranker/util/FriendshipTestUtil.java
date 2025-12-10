package com.berkay.ranker.util;

import com.berkay.ranker.friendship.controller.request.SendFriendshipRequest;
import com.berkay.ranker.friendship.data.dto.FriendshipDTO;
import com.berkay.ranker.friendship.data.entity.Friendship;

public class FriendshipTestUtil {

    public static FriendshipDTO getFriendshipDTO(Long index, Long receiverId){
        FriendshipDTO friendshipDTO = new FriendshipDTO();
        friendshipDTO.setId(index);
        friendshipDTO.setSenderId(index);
        friendshipDTO.setReceiverId(receiverId);
        return friendshipDTO;
    }

    public static Friendship getFriendship(Long index, boolean replied, boolean answer){
        Friendship friendship = new Friendship();
        friendship.setId(index);
        friendship.setReplied(replied);
        friendship.setAnswer(answer);
        return friendship;
    }

    public static SendFriendshipRequest getSendFriendshipRequest(Long senderId, Long receiverId){
        SendFriendshipRequest request = new SendFriendshipRequest();
        request.setSenderId(senderId);
        request.setReceiverId(receiverId);
        return request;
    }
}
