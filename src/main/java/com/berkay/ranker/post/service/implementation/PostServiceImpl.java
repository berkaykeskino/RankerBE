package com.berkay.ranker.post.service.implementation;

import com.berkay.ranker.common.exceptionHandling.customExceptions.ResourceNotFoundException;
import com.berkay.ranker.post.data.dto.PostDTO;
import com.berkay.ranker.post.data.entity.Post;
import com.berkay.ranker.post.data.mapper.PostMapper;
import com.berkay.ranker.post.data.repository.PostRepository;
import com.berkay.ranker.post.service.PostService;
import com.berkay.ranker.user.data.entity.User;
import com.berkay.ranker.user.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostMapper postMapper;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public PostDTO createPost(PostDTO postDTO){
        User user = userRepository.findById(postDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("user.not.found", postDTO.getUserId()));
        Post post = postMapper.toPost(postDTO);
        post.setUser(user);
        Post savedPost = postRepository.save(post);
        return postMapper.toPostDTO(savedPost);
    }
}
