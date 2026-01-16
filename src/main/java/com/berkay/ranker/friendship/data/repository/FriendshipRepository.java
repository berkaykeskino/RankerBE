package com.berkay.ranker.friendship.data.repository;

import com.berkay.ranker.friendship.data.entity.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
    List<Friendship> findAllBySenderIdOrReceiverId(Long senderId, Long receiverId);
}
