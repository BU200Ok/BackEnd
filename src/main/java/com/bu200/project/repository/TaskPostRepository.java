package com.bu200.project.repository;

import com.bu200.project.entity.TaskPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskPostRepository extends JpaRepository<TaskPost, Long> {

    @Query("SELECT tp FROM TaskPost tp " +
            "WHERE tp.task.project.projectCode = :projectCode " +
            "AND tp.task.taskType = :taskType " +
            "AND tp.task.taskCode = :taskCode " +
            "ORDER BY COALESCE(tp.taskPostModifyTime, tp.taskPostTime) DESC")
    List<TaskPost> findByProjectTaskTypeAndTaskCodeOrderByRecent(
            @Param("projectCode") Long projectCode,
            @Param("taskType") String taskType,
            @Param("taskCode") Long taskCode
    );
}
