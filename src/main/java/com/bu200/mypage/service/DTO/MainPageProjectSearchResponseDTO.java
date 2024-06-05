package com.bu200.mypage.service.DTO;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MainPageProjectSearchResponseDTO {
    private Long projectCode;
    private String projectName;
    private Date projectStart;
    private Date projectEnd;
    private Integer projectPriority;
    private String projectOperations;
    private String projectDescription;
}
