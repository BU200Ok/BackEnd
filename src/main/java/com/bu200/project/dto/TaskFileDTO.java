package com.bu200.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class TaskFileDTO {
    private Long taskFileCode;
    private String taskFileName;
    private String taskFileSize;
    private String taskFilePath;
}
