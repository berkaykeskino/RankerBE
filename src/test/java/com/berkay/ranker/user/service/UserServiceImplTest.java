package com.berkay.ranker.user.service;

import com.berkay.ranker.common.exceptionHandling.customExceptions.DuplicateResourceException;
import com.berkay.ranker.common.exceptionHandling.customExceptions.ResourceNotFoundException;
import com.berkay.ranker.friendship.data.dto.FriendshipDTO;
import com.berkay.ranker.friendship.data.entity.Friendship;
import com.berkay.ranker.friendship.data.mapper.FriendshipMapper;
import com.berkay.ranker.post.data.dto.PostDTO;
import com.berkay.ranker.post.data.entity.Post;
import com.berkay.ranker.post.data.mapper.PostMapper;
import com.berkay.ranker.user.data.dto.UserDTO;
import com.berkay.ranker.user.data.entity.Role;
import com.berkay.ranker.user.data.entity.User;
import com.berkay.ranker.user.data.mapper.UserMapper;
import com.berkay.ranker.user.data.repository.RoleRepository;
import com.berkay.ranker.user.data.repository.UserRepository;
import com.berkay.ranker.user.service.implementation.UserServiceImpl;
import com.berkay.ranker.util.FriendshipTestUtil;
import com.berkay.ranker.util.PostTestUtil;
import com.berkay.ranker.util.UserTestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl userServiceImpl;
    @Mock
    private UserMapper userMapper;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PostMapper postMapper;
    @Mock
    private FriendshipMapper friendshipMapper;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private RoleRepository roleRepository;

    @Test
    void createUser_ThrowsDuplicateResourceException_WhenUsernameExists(){
        UserDTO userDTO = UserTestUtil.getUserDTO(1L);
        when(userRepository.existsByUsername("username1")).thenReturn(true);
        Exception exception = assertThrows(DuplicateResourceException.class, () -> userServiceImpl.createUser(userDTO));
        assertEquals("user.username.already.exists", exception.getMessage());
    }

    @Test
    void createUser_ReturnsSavedUser(){
        UserDTO userDTO = UserTestUtil.getUserDTO(1L);
        User user = UserTestUtil.getUser(1L);
        when(userRepository.existsByUsername("username1")).thenReturn(false);
        when(userMapper.toUser(userDTO)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.toUserDTO(user)).thenReturn(userDTO);
        when(passwordEncoder.encode(any())).thenReturn("password");
        when(roleRepository.findById(1L)).thenReturn(Optional.of(new Role()));
        UserDTO response = userServiceImpl.createUser(userDTO);
        assertEquals("username1", response.getUsername());
    }

    @Test
    void getAllPosts_ReturnsPostDTOList(){
        List<Post> postList = List.of(
                PostTestUtil.getPost(1L),
                PostTestUtil.getPost(2L)
        );
        List<PostDTO> postDTOList = List.of(
                PostTestUtil.getPostDTO(1L, 1L),
                PostTestUtil.getPostDTO(2L, 1L)
        );
        User user = UserTestUtil.getUser(1L);
        user.setPosts(postList);
        when(userRepository.findByUsername(any())).thenReturn(user);
        when(postMapper.toPostDTO(postList.get(0))).thenReturn(postDTOList.get(0));
        when(postMapper.toPostDTO(postList.get(1))).thenReturn(postDTOList.get(1));
        List<PostDTO> response = userServiceImpl.getAllPosts("username");
        assertEquals(2, response.size());
        assertEquals(1L, response.get(0).getId());
        assertEquals(1L, response.get(1).getUserId());
    }

    @Test
    void getPendingRequests_ThrowsResourceNotFoundException_WhenUserDoesNotExist(){
        Long id = 1L;
        when(userRepository.findById(id)).thenReturn(Optional.empty());
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> userServiceImpl.getPendingRequests(id));
        assertEquals("user.not.found", exception.getMessage());
    }

    @Test
    void getPendingRequests_ReturnsPendingFriendshipDTOList(){
        Long userId = 1L;
        Friendship pending = FriendshipTestUtil.getFriendship(1L, false, false);
        Friendship replied = FriendshipTestUtil.getFriendship(2L, true, true);
        FriendshipDTO pendingDTO = FriendshipTestUtil.getFriendshipDTO(1L, 1L);
        List<Friendship> friendships = List.of(pending, replied);
        User user = UserTestUtil.getUser(userId);
        user.setReceivedFriendships(friendships);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(friendshipMapper.toFriendshipDTO(pending)).thenReturn(pendingDTO);
        List<FriendshipDTO> friendshipDTOList = userServiceImpl.getPendingRequests(userId);
        assertEquals(1, friendshipDTOList.size());
        assertEquals(1L, friendshipDTOList.get(0).getId());
    }

    @Test
    void getUser_ReturnsUserDTO(){
        Long userId = 1L;
        User user = UserTestUtil.getUser(userId);
        UserDTO userDTO = UserTestUtil.getUserDTO(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userMapper.toUserDTO(user)).thenReturn(userDTO);
        UserDTO response = userServiceImpl.getUser(userId);
        assertEquals(userId, response.getId());
    }

    @Test
    void  getUser_ThrowsException(){
        when(userRepository.findById(any())).thenReturn(Optional.empty());
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> userServiceImpl.getUser(1L));
        assertEquals("user.not.found", exception.getMessage());
    }
}
