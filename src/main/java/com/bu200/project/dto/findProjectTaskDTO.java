package com.bu200.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class findProjectTaskDTO {
    String keyword;
    String option;
    /**
     * option default = "all"
     */
}
