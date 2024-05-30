package com.bu200.project.dto;

import java.sql.Timestamp;
import java.time.LocalDate;

public class TaskDTO {
    private Long taskCode;
    private String taskName;
    private String taskDetail;
    private String taskType;
    private Timestamp taskStart;
    private String taskStatus;
    private LocalDate taskEnd;
}
