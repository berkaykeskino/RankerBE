package com.berkay.ranker.friendship.service;

import com.berkay.ranker.friendship.data.dto.FriendshipDTO;
import com.berkay.ranker.user.data.dto.UserDTO;

import java.util.List;

public interface FriendshipService {
    FriendshipDTO sendFriendship(Long senderId, Long receiverId);
    List<UserDTO> getNonFriends(Long userId);
    FriendshipDTO respondToFriendship(Long friendshipId, boolean answer);
    List<UserDTO> getFriendList(Long currentUserId);
}
