package com.bu200.mypage.service.DTO;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MyPageAttendanceResponseDTO {
    private String attendanceStatus;
    private LocalDateTime attendanceGoWork;
    private LocalDateTime attendanceLeaveWork;
}
