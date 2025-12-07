package com.berkay.ranker.friendship.data.mapper;

import com.berkay.ranker.friendship.data.dto.FriendshipDTO;
import com.berkay.ranker.friendship.data.entity.Friendship;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FriendshipMapper {
    @Mapping(source = "sender.id", target = "senderId")
    @Mapping(source = "receiver.id", target = "receiverId")
    FriendshipDTO toFriendshipDTO(Friendship friendship);

    @Mapping(source = "senderId", target = "sender.id")
    @Mapping(source = "receiverId", target = "receiver.id")
    Friendship toFriendship(FriendshipDTO friendshipDTO);
}
