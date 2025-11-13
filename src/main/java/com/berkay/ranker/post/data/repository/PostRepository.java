package com.berkay.ranker.post.data.repository;

import com.berkay.ranker.post.data.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
