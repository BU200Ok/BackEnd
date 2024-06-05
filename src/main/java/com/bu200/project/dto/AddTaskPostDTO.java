package com.bu200.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.sql.Timestamp;

@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class AddTaskPostDTO {
    private Long taskPostCode;
    private String taskPostDetail;  //o
    private Timestamp taskPostTime; //o
    private Timestamp taskPostModifyTime;
    private boolean taskPostOpenStatus = true;
}
