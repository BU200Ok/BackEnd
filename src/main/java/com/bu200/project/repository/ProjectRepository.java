package com.bu200.project.repository;

import com.bu200.login.entity.Team;
import com.bu200.project.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    //openstatus : true, 일반유저, 오름차순 == (모든 프로젝트)
    Page<Project> findAllByAccount_Team_TeamCodeAndProjectOpenStatusIsTrueOrderByProjectPriorityAsc(Long teamCode, Pageable pageable);

    //openstatus : true, 관리자, 오름차순
    Page<Project> findAllByProjectOpenStatusIsTrueOrderByProjectPriorityAsc(Pageable pageable);

    //키워드로 프로젝트 검색
    Page<Project> findAllByProjectNameContainingAndProjectOpenStatusIsTrue(String Keyword, Pageable pageable);

    //프로젝트 이름 중복검사
    Project findByProjectNameAndProjectOpenStatusIsTrue(String projectName);

    Project findByProjectCode(Long projectCode);
}
