package com.berkay.ranker.friendship.service.implementation;

import com.berkay.ranker.common.exceptionHandling.customExceptions.ResourceNotFoundException;
import com.berkay.ranker.friendship.data.dto.FriendshipDTO;
import com.berkay.ranker.friendship.data.entity.Friendship;
import com.berkay.ranker.friendship.data.mapper.FriendshipMapper;
import com.berkay.ranker.friendship.data.repository.FriendshipRepository;
import com.berkay.ranker.friendship.service.FriendshipService;
import com.berkay.ranker.user.data.dto.UserDTO;
import com.berkay.ranker.user.data.mapper.UserMapper;
import com.berkay.ranker.user.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendshipServiceImpl implements FriendshipService {
    private final FriendshipMapper mapper;
    private final FriendshipRepository repository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public FriendshipDTO sendFriendship(Long senderId, Long receiverId){
        userRepository.findById(senderId)
                .orElseThrow(() -> new ResourceNotFoundException("user.not.found", senderId));
        userRepository.findById(receiverId)
                .orElseThrow(() -> new ResourceNotFoundException("user.not.found", receiverId));
        Friendship friendship = mapper.toFriendship(createSendFriendshipDTO(senderId, receiverId));
        Friendship savedFriendship = repository.save(friendship);
        return mapper.toFriendshipDTO(savedFriendship);
    }

    private FriendshipDTO createSendFriendshipDTO(Long senderId, Long receiverId){
        FriendshipDTO friendshipDTO = new FriendshipDTO();
        friendshipDTO.setSenderId(senderId);
        friendshipDTO.setReceiverId(receiverId);
        friendshipDTO.setReplied(false);
        friendshipDTO.setAnswer(false);
        return friendshipDTO;
    }

    @Override
    public List<UserDTO> getNonFriends(Long userId){
        return userRepository.findNonFriends(userId).stream()
                .map(userMapper::toUserDTO)
                .toList();
    }

    @Override
    public FriendshipDTO respondToFriendship(Long friendshipId, boolean answer) {
        Friendship friendship = repository.findById(friendshipId)
                .orElseThrow(() -> new ResourceNotFoundException("Friendship not found", friendshipId));

        friendship.setReplied(true);
        friendship.setAnswer(answer);

        Friendship savedFriendship = repository.save(friendship);
        return mapper.toFriendshipDTO(savedFriendship);
    }

    @Override
    public List<UserDTO> getFriendList(Long currentUserId) {
        List<Friendship> allFriendships = repository.findAllBySenderIdOrReceiverId(currentUserId, currentUserId);

        return allFriendships.stream()
                .filter(friendship -> friendship.isReplied() && friendship.isAnswer())
                .map(friendship -> {
                    if (friendship.getSender().getId().equals(currentUserId)) {
                        return friendship.getReceiver();
                    } else {
                        return friendship.getSender();
                    }
                })
                .map(userMapper::toUserDTO)
                .toList();
    }
}
