package com.bu200.login.dto;

import com.bu200.login.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor@NoArgsConstructor
@Getter@Setter
public class TeamDTO {
    private Long teamCode;
    private String teamName;
    private Department departmentCode;
}
