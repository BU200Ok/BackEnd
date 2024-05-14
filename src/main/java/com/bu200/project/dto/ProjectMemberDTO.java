package com.bu200.project.dto;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Setter@Getter@ToString
public class ProjectMemberDTO {
    private Long projectMemberCode;
    private Long projectCode;
    private AccountDTO accountCode;
}
