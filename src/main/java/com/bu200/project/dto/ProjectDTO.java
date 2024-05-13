package com.bu200.project.dto;

import lombok.*;

import java.util.Date;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter@ToString
public class ProjectDTO {
    private Long projectCode;
    private String projectName;
    private Date projectStart;
    private Date projectEnd;
    private String projectStatus;
    private String projectDescription;
    private String projectOpenStatus;
    private Integer projectPriority;
    private String accountName;

    private List<String> members;
}
