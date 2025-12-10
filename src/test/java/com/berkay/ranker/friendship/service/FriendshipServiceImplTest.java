package com.berkay.ranker.friendship.service;

import com.berkay.ranker.common.exceptionHandling.customExceptions.ResourceNotFoundException;
import com.berkay.ranker.friendship.data.dto.FriendshipDTO;
import com.berkay.ranker.friendship.data.entity.Friendship;
import com.berkay.ranker.friendship.data.mapper.FriendshipMapper;
import com.berkay.ranker.friendship.data.repository.FriendshipRepository;
import com.berkay.ranker.friendship.service.implementation.FriendshipServiceImpl;
import com.berkay.ranker.user.data.entity.User;
import com.berkay.ranker.user.data.repository.UserRepository;
import com.berkay.ranker.util.FriendshipTestUtil;
import com.berkay.ranker.util.UserTestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FriendshipServiceImplTest {
    @InjectMocks
    private FriendshipServiceImpl service;
    @Mock
    private FriendshipMapper mapper;
    @Mock
    private FriendshipRepository repository;
    @Mock
    private UserRepository userRepository;

    @Test
    void sendFriendship_ThrowsResourceNotFoundException_WhenSenderUserNotFound(){
        Long senderId = 1L;
        Long receiverId = 2L;
        when(userRepository.findById(senderId)).thenReturn(Optional.empty());
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> service.sendFriendship(senderId, receiverId));
        assertEquals("user.not.found", exception.getMessage());
    }

    @Test
    void sendFriendship_ThrowsResourceNotFoundException_WhenReceiverUserNotFound(){
        Long senderId = 1L;
        Long receiverId = 2L;
        when(userRepository.findById(senderId)).thenReturn(Optional.of(new User()));
        when(userRepository.findById(receiverId)).thenReturn(Optional.empty());
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> service.sendFriendship(senderId, receiverId));
        assertEquals("user.not.found", exception.getMessage());
    }

    @Test
    void sendFriendship_ReturnsFriendshipDTO(){
        Long senderId = 1L;
        Long receiverId = 2L;
        User sender = UserTestUtil.getUser(senderId);
        User receiver = UserTestUtil.getUser(receiverId);
        Friendship friendship = FriendshipTestUtil.getFriendship(1L, false, false);
        FriendshipDTO friendshipDTO = FriendshipTestUtil.getFriendshipDTO(senderId, receiverId);
        when(userRepository.findById(senderId)).thenReturn(Optional.of(sender));
        when(userRepository.findById(receiverId)).thenReturn(Optional.of(receiver));
        when(mapper.toFriendship(any())).thenReturn(friendship);
        when(repository.save(friendship)).thenReturn(friendship);
        when(mapper.toFriendshipDTO(friendship)).thenReturn(friendshipDTO);
        FriendshipDTO response = service.sendFriendship(senderId, receiverId);
        assertEquals(senderId, response.getSenderId());
        assertEquals(receiverId, response.getReceiverId());
    }
}
