package com.bu200.mypage.service.Dtos;

import com.bu200.project.entity.Project;
import lombok.*;

import java.util.Date;
import java.util.List;

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
