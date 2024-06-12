package com.bu200.attendance.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter@ToString
public class AttendanceStatusDTO {
    //총 출근 일 수
    private Integer totalCommute;
    //총 지각 일 수
    private Integer totalLateness;
    //남은 연차
    private Integer remainingAnnualLeave;
    //1년에 몇 개의 연차를 받는지
    private Integer totalAnnualOfYear;
    //이번 년도 연차 기록
    private List<AnnualDTO> annuals;
}
