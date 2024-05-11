package com.bu200.mypage.repository;

import com.bu200.login.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface FindAccountDataRepository extends JpaRepository<Account, Long> {
    Account findByAccountId(String accountId);  //find
    //TodoList 컴포넌트
    //TodoList findTodoListByTodoListCode(Long todoListCode);

}
