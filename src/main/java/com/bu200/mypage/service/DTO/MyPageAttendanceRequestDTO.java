package com.bu200.mypage.service.DTO;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter@Setter@ToString
@NoArgsConstructor@AllArgsConstructor
public class MyPageAttendanceRequestDTO {
    private String attendanceStatus;
    private LocalDate attendanceDate;
    private LocalDateTime attendanceGoWork;
    private LocalDateTime attendanceLeaveWork;
}
