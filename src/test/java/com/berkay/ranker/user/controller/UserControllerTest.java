package com.berkay.ranker.user.controller;

import com.berkay.ranker.user.controller.request.CreateUserRequest;
import com.berkay.ranker.user.controller.response.UserResponse;
import com.berkay.ranker.user.data.dto.UserDTO;
import com.berkay.ranker.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @InjectMocks
    private UserController userController;
    @Mock
    private UserService userService;

    @Test
    void createUser_GivenExistingUser_ThrowsException(){
        when(userService.createUser(any())).thenThrow(new RuntimeException("Username already exists: "+ "1"));
        Exception exception = assertThrows(RuntimeException.class, () -> userController.createUser(new CreateUserRequest()));
        assertEquals("Username already exists: "+ "1", exception.getMessage());
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
}
