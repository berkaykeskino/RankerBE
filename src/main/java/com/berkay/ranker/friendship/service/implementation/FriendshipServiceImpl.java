package com.berkay.ranker.friendship.service.implementation;

import com.berkay.ranker.common.exceptionHandling.customExceptions.ResourceNotFoundException;
import com.berkay.ranker.friendship.data.dto.FriendshipDTO;
import com.berkay.ranker.friendship.data.entity.Friendship;
import com.berkay.ranker.friendship.data.mapper.FriendshipMapper;
import com.berkay.ranker.friendship.data.repository.FriendshipRepository;
import com.berkay.ranker.friendship.service.FriendshipService;
import com.berkay.ranker.user.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FriendshipServiceImpl implements FriendshipService {
    private final FriendshipMapper mapper;
    private final FriendshipRepository repository;
    private final UserRepository userRepository;

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
}
