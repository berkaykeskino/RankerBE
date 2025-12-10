package com.berkay.ranker.user.service.implementation;

import com.berkay.ranker.common.exceptionHandling.customExceptions.DuplicateResourceException;
import com.berkay.ranker.common.exceptionHandling.customExceptions.ResourceNotFoundException;
import com.berkay.ranker.friendship.data.dto.FriendshipDTO;
import com.berkay.ranker.friendship.data.mapper.FriendshipMapper;
import com.berkay.ranker.post.data.dto.PostDTO;
import com.berkay.ranker.post.data.mapper.PostMapper;
import com.berkay.ranker.user.data.dto.UserDTO;
import com.berkay.ranker.user.data.entity.User;
import com.berkay.ranker.user.data.mapper.UserMapper;
import com.berkay.ranker.user.data.repository.UserRepository;
import com.berkay.ranker.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PostMapper postMapper;
    private final FriendshipMapper friendshipMapper;

    @Override
    public UserDTO createUser(UserDTO userDTO){
        UserDTO savedUserDTO;
        if (userRepository.existsByUsername(userDTO.getUsername())){
            throw new DuplicateResourceException("user.username.already.exists", userDTO.getUsername());
        }else{
            User savedUser = userRepository.save(userMapper.toUser(userDTO));
            savedUserDTO = userMapper.toUserDTO(savedUser);
        }
        return savedUserDTO;
    }

    @Override
    public List<PostDTO> getAllPosts(String username){
        return userRepository.findByUsername(username)
                .getPosts()
                .stream()
                .map(postMapper::toPostDTO)
                .toList();
    }

    @Override
    public List<FriendshipDTO> getPendingRequests(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user.not.found", userId))
                .getReceivedFriendships()
                .stream()
                .filter(friendship -> !friendship.isReplied())
                .map(friendshipMapper::toFriendshipDTO)
                .toList();
    }
}
