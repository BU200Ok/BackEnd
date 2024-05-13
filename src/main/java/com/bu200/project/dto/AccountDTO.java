package com.bu200.project.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter@Getter
@ToString
public class AccountDTO {
    private Long accountCode;
    private String accountName;
}
