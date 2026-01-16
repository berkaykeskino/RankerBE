package com.berkay.ranker.user.data.repository;

import com.berkay.ranker.user.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
    User findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.id != :currentUserId AND u.id NOT IN " +
            "(SELECT f.receiver.id FROM Friendship f WHERE f.sender.id = :currentUserId) AND u.id NOT IN " +
            "(SELECT f.sender.id FROM Friendship f WHERE f.receiver.id = :currentUserId)")
    List<User> findNonFriends(@Param("currentUserId") Long currentUserId);
}
