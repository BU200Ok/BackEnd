package com.bu200.mypage.repository;

import com.bu200.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


//mypage의 project를 찾아오기 위한 리포지토리   1개의 프로젝트만 진행한다고 가정
public interface FindMyPageProjectRepository extends JpaRepository<Project, Long> {
    Project findProjectByAccount_AccountIdOrderByProjectPriority(String accountId);

    List<Project> findProjectByAccount_AccountIdAndProjectNameContaining(String accountId, String keyword);
}
