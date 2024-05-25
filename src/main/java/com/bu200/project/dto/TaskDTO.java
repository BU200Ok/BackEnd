package com.bu200.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor
public class TaskDTO {  //추가 시 줘야할 부분 o로 표시
    private Long taskCode;
    private String taskName;    //o
    private String taskDetail;  //o
    private String taskType;
    private Timestamp taskStart;
    private String taskStatus;  //o
    private LocalDate taskEnd;  //o
}
