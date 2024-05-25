package com.bu200.project.dto;

import com.bu200.login.entity.Account;
import com.bu200.login.entity.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddProjectDTO {    //request로 받아야 할 부분 주석으로 o 표시
    private Long projectCode;
    private String projectName; //o
    private Timestamp projectStart;
    private LocalDate projectEnd;   //o
    private Integer projectPriority;    //o
    private Boolean projectOpenStatus = true;
    private String projectDescription;  // o
    private String projectStatus = "기획중";

    private String accountName;
    private String teamName;
}
