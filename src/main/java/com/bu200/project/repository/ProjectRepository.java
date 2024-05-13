package com.bu200.project.repository;

import com.bu200.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project,Long> {
    Project findByProjectName(String projectName);
}
