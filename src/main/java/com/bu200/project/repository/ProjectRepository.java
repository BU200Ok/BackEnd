package com.bu200.project.repository;

import com.bu200.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project,Long> {
    Project findByProjectName(String projectName);
    List<Project> findAllByProjectCodeIn(List<Long> memberCode);

    List<Project> findAllByProjectOpenStatusTrue();

    List<Project> findAllByProjectCode(Long projectCode);
    Project findByProjectCode(Long ProjectCode);
}
