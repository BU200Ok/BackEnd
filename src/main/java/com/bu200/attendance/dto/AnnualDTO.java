package com.bu200.attendance.dto;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter@ToString
public class AnnualDTO {
    private Long annualCode;
    private String annualType;
    private LocalDate annualStart;
    private LocalDate annualEnd;
    private int annualCount;
}
