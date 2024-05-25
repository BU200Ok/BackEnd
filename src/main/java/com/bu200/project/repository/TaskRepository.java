package com.bu200.project.repository;

import com.bu200.project.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Page<Task> findAllByProject_ProjectCodeAndTaskType(Long projectCode, String taskType, Pageable pageable);

    Task findByProject_ProjectCodeAndTaskNameAndTaskType(Long projectCode, String taskName, String taskType);

    Task findByTaskCode(Long taskCode);
}
