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

    private Long projectCode;   //프로젝트 키값
    private String projectName; //프로젝트 이름
    private LocalDate projectStart; //프로젝트 시작 시간
    private LocalDate projectEnd;   //프로젝트 마감일
    private String projectStatus;   //프로젝트 상태(진행중, 기획중 등등)
    private String projectDescription;  //프로젝트 설명
    private String accountName; //담당자 이름
    private String accountEmail;    //담당자 이메일
    private String departmentName;  //담당자 부서
    private String teamName;    //담당자 팀이름

    private List<AccountDTO> accounts;  //프로젝트에 포함된 유저들
}
