package com.bu200.project.repository;

import com.bu200.project.entity.AccountProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountProjectRepository extends JpaRepository<AccountProject, Long> {
    AccountProject findByAccount_AccountIdAndProject_ProjectCode(String AccountId, Long projectCode);

    AccountProject findByAccount_AccountCodeAndProject_ProjectCode(Long accountCode, Long projectCode);
}
