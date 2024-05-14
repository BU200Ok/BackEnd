package com.bu200.project.dto;

import com.bu200.login.dto.TeamDTO;
import com.bu200.login.entity.Team;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter@Getter
@ToString
public class AccountDTO {
    private Long accountCode;
    private String accountName;
    private Team team;
}
