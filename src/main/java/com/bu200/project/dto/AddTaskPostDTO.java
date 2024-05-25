package com.bu200.project.dto;

import com.bu200.project.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class AddTaskPostDTO {
    private Long taskPostCode;
    private String taskPostDetail;
    private Timestamp taskPostTime;
    private Timestamp taskPostModifyTime;
}
