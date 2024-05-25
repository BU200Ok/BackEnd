package com.bu200.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor
public class TaskPostDTO {
    private Long taskPostCode;
    private String taskPostDetail;
    private Timestamp taskPostTime;
    private Timestamp taskPostModifyTime;

    private String accountName;
}
