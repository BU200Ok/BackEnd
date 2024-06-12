package com.bu200.mypage.repository;

import com.bu200.login.entity.Account;
import com.bu200.login.entity.Attendance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FindAccountDataRepository extends JpaRepository<Account, Long> {
    //account, project, attendance, team, department
    @Query("select a from Account a " +
            "left join fetch a.projects p " +
            "left join fetch a.team t " +
            "left join fetch t.department d " +
            "where a.accountId = :accountId")
    Account findAccountWithHighPriorityProject(@Param("accountId") String accountId);

}
