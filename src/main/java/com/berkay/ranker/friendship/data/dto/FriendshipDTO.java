package com.berkay.ranker.friendship.data.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FriendshipDTO {
    private Long id;
    private Long senderId;
    private Long receiverId;
    private boolean replied;
    private boolean answer;
}
