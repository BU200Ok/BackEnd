package com.bu200.project.repository;

import com.bu200.project.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT t.taskName from Task t " +
            "where t.project.projectCode = :projectCode " +
            "AND t.taskStatus = :taskStatus " +
            "AND t.taskType = :taskType")
    List<String> findTaskNames(@Param("projectCode") Long projectCode,
                               @Param("taskStatus") String taskStatus,
                               @Param("taskType") Integer taskType);

    @Query("select t.taskCode from Task t " +
            "where t.project.projectCode = :projectCode " +
            "and t.taskType = :taskType " +
            "and t.taskTypeDetail = :taskTypeDetail " +
            "and t.taskName like %:keyword%")
    Page<Long> findKeywordTaskCodes(@Param("projectCode")Long projectCode,
                                    @Param("taskType")Integer taskType,
                                    @Param("taskTypeDetail") String taskTypeDetail,
                                    @Param("keyword") String keyword,
                                    Pageable pageable);

    Task findTaskByProject_ProjectCodeAndTaskTypeAndTaskDetailAndTaskName(Long projectCode,
                                                                          Integer taskType,
                                                                          String taskDetail,
                                                                          String taskName);

    @Query("select t.taskCode from Task t " +
            "where t.project.projectCode = :projectCode " +
            "and t.taskType = :taskType " +
            "and t.taskTypeDetail = :taskTypeDetail"
            )
    Page<Long> findAllTaskCodes(@Param("projectCode")Long projectCode,
                               @Param("taskType")Integer taskType,
                               @Param("taskTypeDetail")String taskTypeDetail,
                               Pageable pageable);

    @Query("select distinct t from Task t " +
            "left join fetch t.accountTasks at " +
            "left join fetch at.account " +
            "where t.taskCode in :taskCodes")
    List<Task> findAllTasks(@Param("taskCodes") List<Long> taskCodes);

    Task findTaskByTaskCode(Long taskCode);
}
