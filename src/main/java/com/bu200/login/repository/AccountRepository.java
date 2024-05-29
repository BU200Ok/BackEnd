package com.bu200.login.repository;

import com.bu200.login.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account,Long> {
    Account findByAccountEmail(String email);

    Boolean existsByAccountId(String accountId);

    Account findByAccountId(String accountId);
    Account findByAccountCode(Long accountCode);

    List<Account> findAllByTeam_TeamCode(Long teamCode);
}
