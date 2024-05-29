package com.bu200.mypage.service.Dtos;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MyPageAttendanceLeaveRequestDTO {  //퇴근 버튼을 눌렀을 때 이DTO로 받아온다.
    private String attendanceStatus = "퇴근";
    private LocalDateTime attendanceLeaveWork;
}
