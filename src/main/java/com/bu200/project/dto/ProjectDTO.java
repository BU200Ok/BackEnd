package com.bu200.project.dto;


import com.bu200.login.dto.TeamDTO;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter@ToString
public class ProjectDTO {
    private Long projectCode;
    private String projectName;
    private LocalDate projectStart;
    private LocalDate projectEnd;
    private String projectStatus;
    private String projectDescription;
    private String projectOpenStatus;
    private Integer projectPriority;
    private String accountName;
    private TeamDTO team;

    private List<ProjectMemberDTO> member;
}
