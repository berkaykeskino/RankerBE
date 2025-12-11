package com.berkay.ranker.likeEvent.data.repository;

import com.berkay.ranker.likeEvent.data.entity.LikeEvent;
import com.berkay.ranker.post.data.entity.Post;
import com.berkay.ranker.user.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeEventRepository extends JpaRepository<LikeEvent, Long> {
    boolean existsByUserAndPost(User user, Post post);
}
