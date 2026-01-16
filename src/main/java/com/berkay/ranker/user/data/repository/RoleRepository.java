package com.berkay.ranker.user.data.repository;

import com.berkay.ranker.user.data.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
