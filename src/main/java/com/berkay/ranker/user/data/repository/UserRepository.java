package com.berkay.ranker.user.data.repository;

import com.berkay.ranker.user.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String userName);
}
