package com.bu200.login.repository;

import com.bu200.login.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account,Long> {
    Account findByAccountEmail(String email);

    Boolean existsByAccountId(String accountId);
    @Query("SELECT d.departmentName FROM Account a JOIN a.team t JOIN t.department d WHERE a.accountId = :userName")
    String findDepartmentNameByUserName(@Param("userName") String userName);

    Account findByAccountId(String accountId);
    Account findByAccountCode(Long accountCode);

    List<Account> findAllByTeam_TeamCode(Long teamCode);
}
