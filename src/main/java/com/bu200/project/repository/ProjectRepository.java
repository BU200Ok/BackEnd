package com.bu200.project.repository;

import com.bu200.project.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project,Long> {
    Project findByProjectName(String projectName);
    Page<Project> findAllByProjectCodeIn(List<Long> memberCode, Pageable pageable);

    Page<Project> findAllByProjectOpenStatusTrue(Pageable pageable);
}