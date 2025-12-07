package com.berkay.ranker.friendship.service;

import com.berkay.ranker.friendship.data.dto.FriendshipDTO;

public interface FriendshipService {
    FriendshipDTO sendFriendship(Long senderId, Long receiverId);
}
