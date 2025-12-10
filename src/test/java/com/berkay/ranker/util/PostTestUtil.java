package com.berkay.ranker.util;

import com.berkay.ranker.post.data.dto.PostDTO;
import com.berkay.ranker.post.data.entity.Post;

public class PostTestUtil {

    public static PostDTO getPostDTO(Long index, Long userId){
        PostDTO postDTO = new PostDTO();
        postDTO.setId(index);
        postDTO.setTitle("Title" + index);
        postDTO.setContent("Content" + index);
        postDTO.setLikeCount(index);
        postDTO.setUserId(userId);
        return postDTO;
    }

    public static Post getPost(Long index){
        Post post = new Post();
        post.setId(index);
        post.setTitle("Title" + index);
        post.setContent("Content" + index);
        post.setLikeCount(index);
        return post;
    }
}
