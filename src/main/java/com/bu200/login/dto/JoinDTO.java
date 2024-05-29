package com.bu200.login.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class JoinDTO {
    private String accountId;
    private String accountPassword;
    private String accountAddress;
    private String accountName;
    private String accountEmail;
    private String accountPosition;
    private String accountRole;
    private String teamName;
}
