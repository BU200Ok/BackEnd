package com.bu200.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//롬복 설정오류로 constructor 임의로생성했음(projectRepository에서 가져오는 부분)
public class ProjectDetailDTO {
    public ProjectDetailDTO(Long projectCode, String projectName, LocalDate projectStart, LocalDate projectEnd, String projectStatus, String projectDescription, String accountName, String accountEmail, String departmentName, String teamName) {
        this.projectCode = projectCode;
        this.projectName = projectName;
        this.projectStart = projectStart;
        this.projectEnd = projectEnd;
        this.projectStatus = projectStatus;
        this.projectDescription = projectDescription;
        this.accountName = accountName;
        this.accountEmail = accountEmail;
        this.departmentName = departmentName;
        this.teamName = teamName;
    }

    private Long projectCode;
    private String projectName;
    private LocalDate projectStart;
    private LocalDate projectEnd;
    private String projectStatus;
    private String projectDescription;
    private String accountName;
    private String accountEmail;
    private String departmentName;
    private String teamName;

    private List<AccountDTO> accounts;
}
