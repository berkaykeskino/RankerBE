package com.berkay.ranker.user.controller;

import com.berkay.ranker.user.controller.Request.CreateUserRequest;
import com.berkay.ranker.user.controller.Response.UserResponse;
import com.berkay.ranker.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Tag(name = "UserController", description = "CRUD services for User entity")
public class UserController {
    private final UserService userService;
    @PostMapping
    @Operation(summary = "Create user")
    public UserResponse createUser(@RequestBody CreateUserRequest createUserRequest){
        return new UserResponse(userService.createUser(createUserRequest.getUserDTO()));
    }
}
