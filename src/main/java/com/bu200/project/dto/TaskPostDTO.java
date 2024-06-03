package com.bu200.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class TaskPostDTO {
    private Long taskPostCode;
    private String taskPostDetail;
    private LocalDate taskPostTime;

    private String accountName;
    private String teamName;

    List<TaskFileDTO> taskFileDTOS;
}
