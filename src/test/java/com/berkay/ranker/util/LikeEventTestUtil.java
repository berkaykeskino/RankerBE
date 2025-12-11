package com.berkay.ranker.util;

import com.berkay.ranker.likeEvent.data.dto.LikeEventDTO;
import com.berkay.ranker.likeEvent.data.entity.LikeEvent;
import com.berkay.ranker.post.data.entity.Post;
import com.berkay.ranker.user.data.entity.User;

public class LikeEventTestUtil {

    public static LikeEventDTO getLikeEventDTO(Long userId, Long postId){
        LikeEventDTO likeEventDTO = new LikeEventDTO();
        likeEventDTO.setUserId(userId);
        likeEventDTO.setPostId(postId);
        return likeEventDTO;
    }

    public static LikeEvent getLikeEvent(Long userId, Long postId){
        LikeEvent likeEvent = new LikeEvent();
        User user = new User();
        user.setId(userId);
        likeEvent.setUser(user);
        Post post = new Post();
        post.setId(postId);
        likeEvent.setPost(post);
        return likeEvent;
    }
}
