package com.bu200.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class AddProjectResponseDTO {
    private Long projectCode;
    private String projectName;
    private LocalDate projectStart;
    private LocalDate projectEnd;
    private Integer projectPriority;
    private boolean projectOpenStatus = true;
    private String projectDescription;
    private String projectStatus;
}
