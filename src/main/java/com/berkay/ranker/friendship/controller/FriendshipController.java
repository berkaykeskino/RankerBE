package com.berkay.ranker.friendship.controller;


import com.berkay.ranker.friendship.controller.request.RespondFriendshipRequest;
import com.berkay.ranker.friendship.controller.request.SendFriendshipRequest;
import com.berkay.ranker.friendship.controller.response.FriendListResponse;
import com.berkay.ranker.friendship.controller.response.NonFriendListResponse;
import com.berkay.ranker.friendship.controller.response.SendFriendshipResponse;
import com.berkay.ranker.friendship.service.FriendshipService;
import com.berkay.ranker.user.controller.response.PendingFriendshipRequestsResponse;
import com.berkay.ranker.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.berkay.ranker.security.model.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/friendship")
@Tag(name = "FriendshipController", description = "CRUD methods for friendship")
public class FriendshipController {
    private final FriendshipService service;
    private final UserService userService;

    @PostMapping("/send")
    @Operation(summary = "Send friendship request")
    public SendFriendshipResponse sendFriendship(@RequestBody SendFriendshipRequest request,
                                                 @AuthenticationPrincipal CustomUserDetails currentUser){
        request.setSenderId(currentUser.getUser().getId());
        return new SendFriendshipResponse(service.sendFriendship(request.getSenderId(), request.getReceiverId()));
    }

    @GetMapping("/non-friend-list")
    @Operation(summary = "Get users that are not my friend")
    public NonFriendListResponse getNonFriends(@AuthenticationPrincipal CustomUserDetails userDetails){
        Long currentUserId = userDetails.getUser().getId();
        return new NonFriendListResponse(service.getNonFriends(currentUserId));
    }

    @GetMapping("/pending")
    @Operation(summary = "Get my pending friend requests")
    public PendingFriendshipRequestsResponse getMyPendingRequests(@AuthenticationPrincipal CustomUserDetails currentUser){
        return new PendingFriendshipRequestsResponse(userService.getPendingRequests(currentUser.getUser().getId()));
    }

    @PostMapping("/respond")
    @Operation(summary = "Accept or Reject a friendship request")
    public SendFriendshipResponse respondToFriendship(@RequestBody RespondFriendshipRequest request){
        return new SendFriendshipResponse(service.respondToFriendship(request.getFriendshipId(), request.isAnswer()));
    }

    @GetMapping("/list")
    @Operation(summary = "Get my accepted friend list")
    public FriendListResponse getFriendList(@AuthenticationPrincipal CustomUserDetails currentUser) {
        Long currentUserId = currentUser.getUser().getId();
        return new FriendListResponse(service.getFriendList(currentUserId));
    }
}
