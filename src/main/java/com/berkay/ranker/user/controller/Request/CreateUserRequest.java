package com.berkay.ranker.user.controller.Request;

import com.berkay.ranker.user.data.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {
    private UserDTO userDTO;
}
