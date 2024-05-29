package com.bu200.project.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class AllProjectResponseDTO {
    private String projectName;
    private LocalDate projectStart;
    private LocalDate projectEnd;
    private String projectStatus;

    private String departmentName;
    private String teamName;

    private List<AllProjectAccountDTO> allProjectAccountDTOS;
}
