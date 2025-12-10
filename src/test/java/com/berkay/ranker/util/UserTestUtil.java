package com.berkay.ranker.util;

import com.berkay.ranker.user.data.dto.UserDTO;
import com.berkay.ranker.user.data.entity.User;

public class UserTestUtil {

    public static UserDTO getUserDTO(Long index){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(index);
        userDTO.setUsername("username" + index);
        userDTO.setPassword("password" + index);
        return userDTO;
    }

    public static User getUser(Long index){
        User user = new User();
        user.setId(index);
        user.setUsername("username" + index);
        user.setPassword("password" + index);
        return user;
    }
}
