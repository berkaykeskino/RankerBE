package com.berkay.ranker.user.controller.Response;

import com.berkay.ranker.user.data.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserResponse {
    private UserDTO userDTO;
}
