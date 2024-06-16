package com.bu200.project.repository;

import com.bu200.project.entity.TaskPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskPostRepository extends JpaRepository<TaskPost, Long> {
    @Query("select tp from TaskPost tp " +
            "join fetch tp.account a " +
            "left join fetch a.team t " +
            "left join fetch tp.taskFiles tf " +
            "where tp.task.taskCode = :taskCode")
    List<TaskPost> findAllTaskPost(@Param("taskCode")Long taskCode);

    TaskPost findTaskPostByTaskPostCode(Long taskPostCode);
}
