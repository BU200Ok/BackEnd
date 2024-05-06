package com.bu200.security.repository;

import com.bu200.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * 유저가 존재하는지 확인하는 메서드
     * @param accountId 확인할 ID
     * @return true or false
     */
    Boolean existsByAccountId(String accountId);

    User findByAccountId(String username);
}
