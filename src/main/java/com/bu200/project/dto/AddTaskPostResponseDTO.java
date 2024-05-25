package com.bu200.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddTaskPostResponseDTO {
    private Long taskPostCode;
    private String taskPostDetail;
    private Timestamp taskPostTime;
    private Timestamp taskPostModifyTime;

    private String accountName;
}
