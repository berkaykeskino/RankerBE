package com.berkay.ranker.likeEvent.data.mapper;

import com.berkay.ranker.likeEvent.data.dto.LikeEventDTO;
import com.berkay.ranker.likeEvent.data.entity.LikeEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LikeEventMapper {
    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "postId", target = "post.id")
    LikeEvent toLikeEvent(LikeEventDTO likeEventDTO);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "post.id", target = "postId")
    LikeEventDTO toLikeEventDTO(LikeEvent likeEvent);
}
