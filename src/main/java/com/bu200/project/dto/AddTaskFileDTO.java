package com.bu200.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class AddTaskFileDTO {
    private Long taskFileCode;
    private String taskFileName;
    private Long taskFileSize;
    private Timestamp taskFileCreateTime;
    private String taskFileType;
    private String taskFilePath;
}
