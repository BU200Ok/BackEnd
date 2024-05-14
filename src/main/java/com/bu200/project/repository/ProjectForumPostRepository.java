package com.bu200.project.repository;

import com.bu200.project.entity.ProjectForumPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectForumPostRepository extends JpaRepository<ProjectForumPost,Long> {
    List<ProjectForumPost> findAllByProjectForumCodeOrderByProjectForumPostWriteDateDesc(Long projectForumCode);
}
