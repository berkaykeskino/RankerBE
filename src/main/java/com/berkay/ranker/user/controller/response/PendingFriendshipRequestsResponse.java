package com.berkay.ranker.user.controller.response;

import com.berkay.ranker.friendship.data.dto.FriendshipDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PendingFriendshipRequestsResponse {
    List<FriendshipDTO> friendshipDTOList;
}
