package com.bu200.project.dto;

import com.bu200.login.entity.Team;
import lombok.*;

import java.time.LocalDate;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor@ToString
public class ProjectDTO {
    private Long projectCode;
    private String projectName;
    private LocalDate projectStart;
    private LocalDate projectEnd;
    private String projectDescription;
    private String projectStatus;

    private String teamName;
    private String departmentName;
}
