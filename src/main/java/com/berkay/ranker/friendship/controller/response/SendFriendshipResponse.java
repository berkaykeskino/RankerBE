package com.berkay.ranker.friendship.controller.response;

import com.berkay.ranker.friendship.data.dto.FriendshipDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SendFriendshipResponse {
    private FriendshipDTO friendshipDTO;
}
