package com.bu200.login.repository;

import com.bu200.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByAccountId(String username);
}
