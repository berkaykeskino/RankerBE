package com.berkay.ranker.friendship.data.repository;

import com.berkay.ranker.friendship.data.entity.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
}
