package com.berkay.ranker.friendship.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendFriendshipRequest {
    private Long senderId;
    private Long receiverId;
}
