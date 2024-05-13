package com.bu200.project.repository;

import com.bu200.login.entity.Account;
import com.bu200.project.entity.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Long> {
    List<ProjectMember> findByAccountCode(Account userCode);
}
