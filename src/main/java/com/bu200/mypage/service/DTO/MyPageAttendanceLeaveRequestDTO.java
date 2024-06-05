package com.bu200.mypage.service.DTO;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MyPageAttendanceLeaveRequestDTO {  //퇴근 버튼을 눌렀을 때 이DTO로 받아온다.
    private LocalDateTime attendanceLeaveWork;
}
