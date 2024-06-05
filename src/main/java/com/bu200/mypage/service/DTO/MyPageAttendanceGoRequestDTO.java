package com.bu200.mypage.service.DTO;

import lombok.*;

import java.time.LocalDateTime;

@Getter@Setter@ToString
@NoArgsConstructor@AllArgsConstructor
public class MyPageAttendanceGoRequestDTO { //출근 버튼을 눌렀을때 이 DTO로 받아온다.
    private String attendanceStatus = "정상 처리";
    private LocalDateTime attendanceGoWork;
}
