package com.berkay.ranker.user.controller;

import com.berkay.ranker.user.controller.request.CreateUserRequest;
import com.berkay.ranker.user.controller.response.AllPostsResponse;
import com.berkay.ranker.user.controller.response.PendingFriendshipRequestsResponse;
import com.berkay.ranker.user.controller.response.UserResponse;
import com.berkay.ranker.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    @GetMapping("/post/{username}")
    @Operation(summary = "Get all posts of the user with the given user name")
    public AllPostsResponse getAllPosts(@PathVariable("username") String username){
        return new AllPostsResponse(userService.getAllPosts(username));
    }

    @GetMapping("/friendship/pending/{userId}")
    @Operation(summary = "Get pending requests of the user with the given user id")
    public PendingFriendshipRequestsResponse getPendingRequests(@PathVariable("userId") Long userId){
        return new PendingFriendshipRequestsResponse(userService.getPendingRequests(userId));
    }
}
