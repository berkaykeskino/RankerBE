package com.berkay.ranker.user.controller.response;

import com.berkay.ranker.post.data.dto.PostDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class AllPostsResponse {
    List<PostDTO> allPosts;
}
