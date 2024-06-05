package com.bu200.project.repository;

import com.bu200.project.entity.TaskFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskFileRepository extends JpaRepository<TaskFile, Long> {

}
