package com.berkay.ranker.post.data.mapper;

import com.berkay.ranker.post.data.dto.PostDTO;
import com.berkay.ranker.post.data.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {
    Post toPost(PostDTO postDTO);

    @Mapping(source = "user.id", target = "userId")
    PostDTO toPostDTO(Post post);
}
