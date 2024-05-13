package com.bu200.login.dto;

import com.bu200.login.entity.Team;
import lombok.*;

import java.util.Date;

@Getter@Setter@ToString
@NoArgsConstructor @AllArgsConstructor
public class AccountDTO {
    private Long accountCode;
    private String accountPassword;
    private String accountName;
    private String accountAddress;
    private String accountEmail;
    private Date accountJoinDate;
    private String accountPosition;
    private String accountRole;
    private Team team;
    private String accountId;
    private Date accountResignDate;
}
