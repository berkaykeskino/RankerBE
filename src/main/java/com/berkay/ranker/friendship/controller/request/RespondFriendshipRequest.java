package com.berkay.ranker.friendship.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RespondFriendshipRequest {
    private Long friendshipId;
    private boolean answer;
}