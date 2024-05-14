package com.bu200.project.repository;

import com.bu200.project.entity.ProjectForum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectForumRepository extends JpaRepository<ProjectForum,Long> {
    List<ProjectForum> findAllByProjectCodeOrderByProjectForumModifyDateDesc(Long projectCode);
}
