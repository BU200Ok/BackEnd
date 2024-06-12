package com.bu200.attendance.controller;

import com.bu200.attendance.dto.AttendanceStatusDTO;
import com.bu200.attendance.entity.Attendance;
import com.bu200.attendance.service.AttendanceService;
import com.bu200.security.dto.CustomUserDetails;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AttendanceController {
    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @GetMapping("/attendance")
    public ResponseEntity<?> getMyAttendance(@AuthenticationPrincipal CustomUserDetails user, @RequestParam int page){
        try{
            Page<Attendance> attendances = attendanceService.findByAccountCode(user.getCode(),page);
            return ResponseEntity.ok().body(attendances);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     *
     * @param user
     * @return 총 출근, 총 지각, 기타, 남은 연차
     */
    @GetMapping("/attendance/status")
    public ResponseEntity<?> getMyAttendanceStatus(@AuthenticationPrincipal CustomUserDetails user){
        try{
            AttendanceStatusDTO attendanceStatusDTO = attendanceService.findMyAttendanceStatus(user);
            return ResponseEntity.ok().body(attendanceStatusDTO);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage()+"의 이유로 실패했습니다.");
        }
    }
}
