package com.berkay.ranker.friendship.controller;


import com.berkay.ranker.friendship.controller.request.SendFriendshipRequest;
import com.berkay.ranker.friendship.controller.response.SendFriendshipResponse;
import com.berkay.ranker.friendship.service.FriendshipService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/friendship")
@Tag(name = "FriendshipController", description = "CRUD methods for friendship")
public class FriendshipController {
    private final FriendshipService service;

    @Operation(summary = "Send friendship request")
    @PostMapping("/send")
    public SendFriendshipResponse sendFriendship(@RequestBody SendFriendshipRequest request){
        return new SendFriendshipResponse(service.sendFriendship(request.getSenderId(), request.getReceiverId()));
    }

}
