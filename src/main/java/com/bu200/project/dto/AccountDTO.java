package com.bu200.project.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter@Getter
@ToString
//프로젝트 참여자를 표시한다.
public class AccountDTO {
    //account 정보
    private Long accountCode;
    private String accountName;
}
