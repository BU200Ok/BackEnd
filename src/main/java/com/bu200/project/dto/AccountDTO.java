package com.bu200.project.dto;

import lombok.*;

@Getter@Setter@NoArgsConstructor
public class AccountDTO {
    public AccountDTO(String accountName, String accountPosition, String teamName, String departmentName) {
        this.accountName = accountName;
        this.accountPosition = accountPosition;
        this.teamName = teamName;
        this.departmentName = departmentName;
    }

    private String accountName;
    private String accountPosition;

    private String teamName;
    private String departmentName;
}
