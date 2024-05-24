package com.bu200.project.dto;

import com.bu200.login.entity.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor
public class ProjectDTO {
    private Long projectCode;
    private String projectName;
    private LocalDate projectStart;
    private LocalDate projectEnd;
    private String projectDescription;
    private String prjectStatus;

    private Team team;
}
