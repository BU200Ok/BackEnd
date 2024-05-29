package com.bu200.project.dto;

import com.bu200.login.entity.Account;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class ProjectDTO {
    private Long projectCode;
    private String projectName;
    private LocalDate projectStart;
    private LocalDate projectEnd;
    private String projectStatus;

    private String departmentName;
    private String teamName;

    private List<AccountDTO> accounts;
}

/**
 * joinfetch로
 * project
 * project.account      ==  담당자
 * project.account.team
 * project.account.team.department
 *
 * project.accountProject
 * project.accountProject.account
 * project.accountProject.account.team
 * project.accountProject.account.team.department 를 한번에 조회할 수 있다.
 */
