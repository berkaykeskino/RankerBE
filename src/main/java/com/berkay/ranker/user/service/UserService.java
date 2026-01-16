package com.berkay.ranker.user.service;

import com.berkay.ranker.friendship.data.dto.FriendshipDTO;
import com.berkay.ranker.post.data.dto.PostDTO;
import com.berkay.ranker.user.data.dto.UserDTO;
import com.berkay.ranker.user.data.entity.User;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);

    UserDTO getUser(Long userId);

    List<PostDTO> getAllPosts(String username);

    List<FriendshipDTO> getPendingRequests(Long userId);

    User findByUsername(String username);

    User save(final User user);
}
