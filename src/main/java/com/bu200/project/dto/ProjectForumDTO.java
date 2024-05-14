package com.bu200.project.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter@ToString
public class ProjectForumDTO {
    private Long projectForumCode;
    private String projectForumName;
    private LocalDateTime projectForumCreateTime;
    private LocalDate projectForumModifyDate;
    private boolean projectForumDeleted;
    private Long projectCode;
    private String projectForumStatus;
}
