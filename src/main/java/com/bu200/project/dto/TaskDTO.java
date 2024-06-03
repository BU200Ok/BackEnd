package com.bu200.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor
public class TaskDTO {
    private Long taskCode;
    private String taskName;
    private String taskDetail;
    private String taskType;
    private String taskTypeDetail;
    private Timestamp taskStart;
    private String taskStatus;
    private LocalDate taskEnd;

    private List<String> accountName;
}
