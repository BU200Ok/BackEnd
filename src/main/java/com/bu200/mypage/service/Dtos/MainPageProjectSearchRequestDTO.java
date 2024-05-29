package com.bu200.mypage.service.Dtos;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MainPageProjectSearchRequestDTO {
    private String accountName;
    private String projectName;
}
