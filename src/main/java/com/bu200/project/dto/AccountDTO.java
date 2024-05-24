package com.bu200.project.dto;

import com.bu200.login.entity.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class AccountDTO {
    private String accountName;
    private Team team;

}
