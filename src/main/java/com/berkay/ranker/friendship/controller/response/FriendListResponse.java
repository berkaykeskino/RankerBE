package com.berkay.ranker.friendship.controller.response;

import com.berkay.ranker.user.data.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class FriendListResponse {
    private List<UserDTO> userDTOList;
}
