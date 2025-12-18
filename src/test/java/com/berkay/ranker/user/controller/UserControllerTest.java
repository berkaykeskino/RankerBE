package com.berkay.ranker.user.controller;

import com.berkay.ranker.common.exceptionHandling.customExceptions.DuplicateResourceException;
import com.berkay.ranker.friendship.data.dto.FriendshipDTO;
import com.berkay.ranker.post.data.dto.PostDTO;
import com.berkay.ranker.user.controller.request.CreateUserRequest;
import com.berkay.ranker.user.controller.response.AllPostsResponse;
import com.berkay.ranker.user.controller.response.PendingFriendshipRequestsResponse;
import com.berkay.ranker.user.controller.response.UserResponse;
import com.berkay.ranker.user.data.dto.UserDTO;
import com.berkay.ranker.user.service.UserService;
import com.berkay.ranker.util.FriendshipTestUtil;
import com.berkay.ranker.util.PostTestUtil;
import com.berkay.ranker.util.UserTestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @InjectMocks
    private UserController userController;
    @Mock
    private UserService userService;

    @Test
    void createUser_GivenExistingUser_ThrowsException(){
        when(userService.createUser(any())).thenThrow(new DuplicateResourceException("User already exists with username: "+ "1"));
        Exception exception = assertThrows(DuplicateResourceException.class, () -> userController.createUser(new CreateUserRequest()));
        assertEquals("User already exists with username: "+ "1", exception.getMessage());
    }

    @Test
    void createUser_GivenNewUser_ReturnsUserDTO(){
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("newUser");
        userDTO.setPassword("newPasswd");
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setUserDTO(userDTO);
        UserDTO savedUser = new UserDTO();
        savedUser.setUsername("newUser");
        savedUser.setPassword("newPasswd");
        savedUser.setId(1L);

        when(userService.createUser(userDTO)).thenReturn(savedUser);
        UserResponse response = userController.createUser(createUserRequest);
        assertEquals(1L, response.getUserDTO().getId());
    }

    @Test
    void getAllPosts_ReturnsAllPostsResponse(){
        PostDTO postDTO1 = PostTestUtil.getPostDTO(1L, 1L);
        PostDTO postDTO2 = PostTestUtil.getPostDTO(2L, 1L);
        when(userService.getAllPosts(any())).thenReturn(List.of(postDTO1, postDTO2));
        AllPostsResponse response = userController.getAllPosts("userName");
        assertEquals(2, response.getAllPosts().size());
    }

    @Test
    void getPendingRequests_ReturnsPendingFriendshipRequestsResponse(){
        FriendshipDTO friendshipDTO1 = FriendshipTestUtil.getFriendshipDTO(1L, 0L);
        FriendshipDTO friendshipDTO2 = FriendshipTestUtil.getFriendshipDTO(2L, 0L);
        when(userService.getPendingRequests(any())).thenReturn(List.of(friendshipDTO1, friendshipDTO2));
        PendingFriendshipRequestsResponse response = userController.getPendingRequests(0L);
        assertEquals(2, response.getFriendshipDTOList().size());
    }

    @Test
    void getUser_ReturnsUserResponse(){
        Long userId = 1L;
        UserDTO userDTO = UserTestUtil.getUserDTO(userId);
        when(userService.getUser(userId)).thenReturn(userDTO);
        UserResponse response = userController.getUser(userId);
        assertEquals(userId, response.getUserDTO().getId());
    }
}
