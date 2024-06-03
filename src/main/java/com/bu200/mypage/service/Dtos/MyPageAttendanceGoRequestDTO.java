package com.bu200.mypage.service.Dtos;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter@Setter@ToString
@NoArgsConstructor@AllArgsConstructor
public class MyPageAttendanceGoRequestDTO { //출근 버튼을 눌렀을때 이 DTO로 받아온다.
    private String attendanceStatus = "출근";
    private LocalDateTime attendanceGoWork;
}
