package com.bu200.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;

@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class AddTaskDTO {
    private Long taskCode;
    private String taskName;    //제목
    private String taskDetail;  //내용
    private Integer taskType;   //분석,설계 등
    private String taskStatus;  //진행중, 진행예정, 진행완료
    private LocalDate taskEnd;  //완료시간
    private String taskTypeDetail;
}
