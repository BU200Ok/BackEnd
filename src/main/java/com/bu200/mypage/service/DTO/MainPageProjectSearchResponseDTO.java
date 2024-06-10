package com.bu200.mypage.service.DTO;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MainPageProjectSearchResponseDTO {
    private Long projectCode;
    private String projectName;
    private LocalDate projectStart;
    private LocalDate projectEnd;
    private Integer projectPriority;
    private String projectOperations;
    private String projectDescription;
}
